package org.soma.weatherviewer.common.binding

import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

@BindingAdapter("photoUrl")
fun bindPhotoUrl(view: ImageView, photoUrl: String) {
    Glide.with(view.context)
        .load(photoUrl)
        .into(view)
}