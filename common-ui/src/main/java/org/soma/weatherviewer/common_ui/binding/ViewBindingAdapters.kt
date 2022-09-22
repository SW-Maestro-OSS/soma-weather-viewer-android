package org.soma.weatherviewer.common_ui.binding

import android.content.ContextWrapper
import android.view.View
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.databinding.BindingAdapter
import dagger.hilt.android.internal.managers.ViewComponentManager

@BindingAdapter("onBackPressed")
fun bindBackButton(view: View, onBackPressed: Boolean) {
    var context = view.context
    // For Hilt
    if (context is ViewComponentManager.FragmentContextWrapper) {
        context = (context as ContextWrapper).baseContext
    }
    if (onBackPressed && context is OnBackPressedDispatcherOwner) {
        view.setOnClickListener {
            context.onBackPressedDispatcher.onBackPressed()
        }
    }
}