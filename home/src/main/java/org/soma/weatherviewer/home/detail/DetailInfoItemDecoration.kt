package org.soma.weatherviewer.home.detail

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DetailInfoItemDecoration() : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = 20
        outRect.right = 20
    }
}