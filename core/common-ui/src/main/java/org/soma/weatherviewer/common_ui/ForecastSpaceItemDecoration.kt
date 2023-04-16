package org.soma.weatherviewer.common_ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.soma.weatherviewer.domain.model.ForecastViewType

class ForecastSpaceItemDecoration(private val space: Int, private val viewType: ForecastViewType) : RecyclerView.ItemDecoration() {

	override fun getItemOffsets(
		outRect: Rect,
		view: View,
		parent: RecyclerView,
		state: RecyclerView.State
	) {
		val position = parent.getChildAdapterPosition(view)

		/**
		 * 첫번째 열이 아닌(None Column-1) 아이템들만 좌측에 [space] 만큼의 여백을 추가한다.
		 * 즉, 첫번째 열에 있는 아이템에는 viewType에 따라 좌측에 여백을 주지 않는다.
		 * */
		if (viewType == ForecastViewType.PORTRAIT && position != 0) {
			outRect.left = space
		} else if (viewType == ForecastViewType.LANDSCAPE && position != 0) {
			outRect.top = space
		}
	}
}