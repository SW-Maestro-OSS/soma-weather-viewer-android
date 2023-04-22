package org.soma.weatherviewer.search

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import org.soma.weatherviewer.common_ui.base.BaseFragment
import org.soma.weatherviewer.search.databinding.FragmentSearchBinding

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

	private val viewModel by viewModels<SearchViewModel>()

	override fun initView() {
		bind {
			vm = viewModel
		}
		setUIEvent()
		subscribeUI()
	}

	private fun setUIEvent() {
		/**
		 * 키보드 엔터 누를 시 [SearchViewModel]의 [setCityName]에서 API 정보를 불러올 수 있는지 체크합니다.
		 * 불러올 수 없다면 도시이름 역시 저장되지 않습니다.
		 */
		binding.searchInputArea.setOnEditorActionListener(object : TextView.OnEditorActionListener {
			override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
				if (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
					val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
					imm.hideSoftInputFromWindow(binding.searchInputArea.windowToken, 0)

					viewModel.setCityName(
						binding.searchInputArea.text.toString(),
						locale = getLocale()
					)

					binding.searchInputArea.text?.clear()
					return true
				}
				return false
			}
		})
	}

	private fun subscribeUI() {
		lifecycleScope.launchWhenStarted {
			viewModel.toastMessage.collectLatest { message ->
				if (message == null || message.isEmpty()) return@collectLatest
				Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
				viewModel.clearToastMessage()
			}
		}
	}

	override fun onStart() {
		super.onStart()
		viewModel.fetchCityWeather(locale = getLocale())
	}
}