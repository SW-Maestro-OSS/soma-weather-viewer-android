package org.soma.weatherviewer.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.soma.weatherviewer.common.MainActivityUtil
import org.soma.weatherviewer.setting.databinding.FragmentSettingBinding

class SettingFragment : Fragment(), SettingFragmentListener {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.listener = this
        }
        return binding.root
    }

    override fun onClickBackButton() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

interface SettingFragmentListener {
    fun onClickBackButton()
}