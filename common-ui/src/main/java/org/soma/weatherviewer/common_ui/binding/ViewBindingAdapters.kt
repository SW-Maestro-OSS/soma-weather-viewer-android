package org.soma.weatherviewer.common_ui.binding

import android.content.ContextWrapper
import android.view.View
import android.widget.ImageView
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.internal.managers.ViewComponentManager

@BindingAdapter("onBackPressed")
fun bindBackButton(view: View, onBackPressed: Boolean) {
    var context = view.context
    // For Hilt
    if (context is ViewComponentManager.FragmentContextWrapper) {
        context = (context as ContextWrapper).applicationContext
    }
    if (onBackPressed && context is OnBackPressedDispatcherOwner) {
        view.setOnClickListener {
            context.onBackPressedDispatcher.onBackPressed()
        }
    }
}

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