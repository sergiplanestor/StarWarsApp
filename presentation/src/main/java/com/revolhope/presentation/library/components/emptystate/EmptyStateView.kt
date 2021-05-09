package com.revolhope.presentation.library.components.emptystate

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.mylibra.databinding.ComponentEmptyStateViewBinding
import com.revolhope.presentation.library.extensions.layoutInflater

class EmptyStateView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    attrStyleDef: Int = 0
) : ConstraintLayout(context, attributes, attrStyleDef) {

    private val binding = ComponentEmptyStateViewBinding.inflate(context.layoutInflater, this, true)

    fun bind(model: EmptyStateUiModel? = null) {
        model?.title?.let { binding.title.text = it }
        model?.description?.let { binding.description.text = it }
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == View.VISIBLE) {
            binding.lottieView.playAnimation()
        } else {
            binding.lottieView.cancelAnimation()
        }
    }
}
