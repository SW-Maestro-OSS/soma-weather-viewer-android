package org.soma.weatherviewer.home.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.soma.weatherviewer.common.domain.model.WeatherModel

class WeatherDetailAdapter(fragment: Fragment, private val size: Int, private val weatherList: List<WeatherModel>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment = WeatherDetailInfoFragment.newInstance(weatherList[position])
}