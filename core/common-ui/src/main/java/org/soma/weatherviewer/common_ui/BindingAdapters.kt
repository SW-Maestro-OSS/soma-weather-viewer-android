package org.soma.weatherviewer.common_ui

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.soma.weatherviewer.domain.model.ForecastVO

@BindingAdapter("imageUrl")
fun bindImage(imageView: AppCompatImageView, imageUrl: String) {
	if (imageUrl.isBlank()) return

	Glide.with(imageView.context)
		.load(imageUrl)
		.into(imageView)
}

@BindingAdapter("forecastItem")
fun RecyclerView.submitForecast(forecastList: List<ForecastVO>) {
	val boundAdapter = this.adapter
	if (boundAdapter is ForecastAdapter) {
		boundAdapter.submitList(forecastList)
	}
}

@BindingAdapter("itemDecoration")
fun RecyclerView.bindItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
	this.addItemDecoration(itemDecoration)
}
