package com.revolhope.presentation.library.components.loader

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.mylibra.databinding.ComponentLoaderViewBinding
import com.revolhope.presentation.library.extensions.layoutInflater

class LoaderView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    attrStyleDef: Int = 0
) : ConstraintLayout(context, attributes, attrStyleDef) {

    private val binding = ComponentLoaderViewBinding.inflate(context.layoutInflater, this, true)

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == View.VISIBLE) {
            binding.lottieView.playAnimation()
        } else {
            binding.lottieView.cancelAnimation()
        }
    }
}
