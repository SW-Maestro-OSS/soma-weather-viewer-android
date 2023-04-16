package org.soma.weatherviewer.common_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.soma.weatherviewer.domain.model.ForecastViewType.LANDSCAPE
import org.soma.weatherviewer.domain.model.ForecastViewType.PORTRAIT
import org.soma.weatherviewer.common_ui.databinding.ItemForecastLandscapeBinding
import org.soma.weatherviewer.common_ui.databinding.ItemForecastPortraitBinding
import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.model.ForecastViewType

class ForecastAdapter(val adapterViewType: ForecastViewType) : ListAdapter<ForecastVO, RecyclerView.ViewHolder>(ForecastDiffUtil) {

	inner class PortraitViewHolder(private val binding: ItemForecastPortraitBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: ForecastVO) {
			binding.item = item
		}
	}

	inner class LandscapeViewHolder(private val binding: ItemForecastLandscapeBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: ForecastVO) {
			binding.item = item
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return when (adapterViewType) {
			PORTRAIT -> PortraitViewHolder(
				ItemForecastPortraitBinding.inflate(
					LayoutInflater.from(parent.context),
					parent,
					false
				)
			)
			LANDSCAPE -> LandscapeViewHolder(
				ItemForecastLandscapeBinding.inflate(
					LayoutInflater.from(parent.context),
					parent,
					false
				)
			)
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		when (adapterViewType) {
			PORTRAIT -> (holder as PortraitViewHolder).bind(currentList[position])
			LANDSCAPE -> (holder as LandscapeViewHolder).bind(currentList[position])
		}
	}
}