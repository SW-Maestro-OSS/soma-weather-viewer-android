package org.soma.weatherviewer.common_ui

import androidx.recyclerview.widget.DiffUtil

object ForecastDiffUtil : DiffUtil.ItemCallback<org.soma.weatherviewer.domain.model.ForecastVO>() {
	override fun areItemsTheSame(oldItem: org.soma.weatherviewer.domain.model.ForecastVO, newItem: org.soma.weatherviewer.domain.model.ForecastVO): Boolean {
		return oldItem == newItem
	}

	override fun areContentsTheSame(oldItem: org.soma.weatherviewer.domain.model.ForecastVO, newItem: org.soma.weatherviewer.domain.model.ForecastVO): Boolean {
		return oldItem.dt == newItem.dt
	}
}