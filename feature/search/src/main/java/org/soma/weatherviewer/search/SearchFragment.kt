package org.soma.weatherviewer.search

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import org.soma.weatherviewer.search.databinding.FragmentSearchBinding

@AndroidEntryPoint
class SearchFragment : Fragment() {

	private lateinit var binding: FragmentSearchBinding
	private val viewModel by viewModels<SearchViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentSearchBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.lifecycleOwner = this@SearchFragment
		super.onViewCreated(view, savedInstanceState)

		with(binding) {
			vm = viewModel

			/**
			 * 키보드 엔터 누를 시 [SearchViewModel]의 [setCityName]에서 API 정보를 불러올 수 있는지 체크합니다.
			 * 불러올 수 없다면 도시이름 역시 저장되지 않습니다.
 			 */
			searchInputArea.setOnEditorActionListener(object: TextView.OnEditorActionListener {
				override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
					if (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
						val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
						imm.hideSoftInputFromWindow(binding.searchInputArea.windowToken, 0)

						viewModel.setCityName(binding.searchInputArea.text.toString())
						binding.searchInputArea.text?.clear()
						return true
					}
					return false
				}
			})
			
			lifecycleScope.launchWhenStarted { 
				viewModel.toastMessage.collectLatest { message ->
					if (message == null || message.isEmpty()) return@collectLatest
					Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
					viewModel.clearToastMessage()
				}
			}
		}
	}

	override fun onStart() {
		super.onStart()
		viewModel.fetchCityWeather()
	}

	override fun onDestroyView() {
		binding.unbind()
		super.onDestroyView()
	}

	companion object {
		@JvmStatic
		fun newInstance() = SearchFragment()
	}
}