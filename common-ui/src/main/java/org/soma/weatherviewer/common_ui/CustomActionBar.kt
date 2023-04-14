package org.soma.weatherviewer.common_ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import org.soma.weatherviewer.common_ui.databinding.LayoutCustomActionBarBinding


class CustomActionBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
): ConstraintLayout(context, attrs, defStyle) {

    lateinit var binding: LayoutCustomActionBarBinding

    init {
        initializeView(context)
        getAttrs(attrs)
    }

    private fun initializeView(context: Context?) {
        binding = LayoutCustomActionBarBinding.inflate(LayoutInflater.from(context), this, true)

        binding.imageBackbutton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomActionBar)
        setTypedArray(typedArray)
    }

    private fun setTypedArray(typedArray: TypedArray) {
        val hasBackButton = typedArray.getBoolean(R.styleable.CustomActionBar_hasBackButton, false)
        binding.imageBackbutton.visibility = when(hasBackButton) {
            true -> View.VISIBLE
            false -> View.GONE
        }

        val title = typedArray.getString(R.styleable.CustomActionBar_title)
        binding.textTitle.text = title
        val titleSize = typedArray.getDimensionPixelSize(R.styleable.CustomActionBar_titleSize, 0)
        if (titleSize > 0) binding.textTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize.toFloat())

        typedArray.recycle()
    }


    fun setHasBackButton(hasBackButton: Boolean) {
        binding.imageBackbutton.visibility = when(hasBackButton) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    fun setTitle(title: String) {
        binding.textTitle.text = title
    }
}