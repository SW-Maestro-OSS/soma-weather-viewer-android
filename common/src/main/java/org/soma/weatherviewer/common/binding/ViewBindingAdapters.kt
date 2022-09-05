package org.soma.weatherviewer.common.binding

import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("photoUrl")
fun bindPhotoUrl(view: ImageView, photoUrl: String?) {
    Glide.with(view.context)
        .load(photoUrl)
        .into(view)
}

@BindingAdapter("adapter", "submitList", requireAll = true)
fun bindRecyclerView(view: RecyclerView, adapter: RecyclerView.Adapter<*>, submitList: List<Any>?) {
    view.adapter = adapter.apply {
        stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        (this as ListAdapter<Any, *>).submitList(submitList?.toMutableList())
    }
}