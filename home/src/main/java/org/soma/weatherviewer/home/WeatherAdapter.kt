package org.soma.weatherviewer.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.soma.weatherviewer.common.model.entity.Weather
import org.soma.weatherviewer.home.databinding.ItemWeatherBinding

class WeatherAdapter: ListAdapter<Weather, RecyclerView.ViewHolder>(WeatherDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemWeatherBinding>(LayoutInflater.from(parent.context), R.layout.item_weather, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val weatherModel = getItem(position)
        (holder as WeatherViewHolder).bind(weatherModel)
    }

    class WeatherViewHolder(private val binding: ItemWeatherBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) {
            binding.weather = weather
            binding.executePendingBindings()
        }
    }
}

private class WeatherDiffCallback: DiffUtil.ItemCallback<Weather>() {
    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem == newItem
    }
}