package org.soma.weatherviewer.common_ui

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
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

@BindingAdapter("show")
fun ProgressBar.bindShow(result: Boolean) {
	visibility = if (result) View.VISIBLE else View.GONE
}

@BindingAdapter("textHide")
fun TextView.setVisibility(text: String) {
	visibility = if (text.isEmpty()) View.GONE else View.VISIBLE
}