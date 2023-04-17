package org.soma.weatherviewer.search

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
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
			 * 키보드 엔터 누를 시 [SearchViewModel]의 [cityName]이 수정되고 api를 호출하게 됩니다.
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
		}
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