package org.soma.weatherviewer.common_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import java.util.*

abstract class BaseFragment<T : ViewDataBinding>(
	@LayoutRes private val layoutId: Int
) : Fragment() {

	lateinit var binding: T

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.lifecycleOwner = this@BaseFragment
		initView()
		super.onViewCreated(view, savedInstanceState)
	}

	abstract fun initView()

	override fun onDestroyView() {
		binding.unbind()
		super.onDestroyView()
	}

	protected inline fun bind(block: T.() -> Unit) {
		binding.apply(block)
	}

	protected fun getLocale(): Locale =
		requireContext().resources.configuration.locales[0]
}