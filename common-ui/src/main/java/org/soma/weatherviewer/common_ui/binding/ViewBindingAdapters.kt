package org.soma.weatherviewer.common_ui.binding

import android.content.ContextWrapper
import android.view.View
import android.widget.TextView
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

@BindingAdapter("textByResId")
fun bindTextByStringRes(view: TextView, resId: Int?) {
    resId?.let {
        view.text = view.context.getString(it)
    }
}