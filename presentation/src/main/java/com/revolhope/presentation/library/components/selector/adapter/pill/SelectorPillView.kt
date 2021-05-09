package com.revolhope.presentation.library.components.selector.adapter.pill

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ComponentSelectorPillViewBinding
import com.revolhope.presentation.library.extensions.color
import com.revolhope.presentation.library.extensions.drawable
import com.revolhope.presentation.library.extensions.layoutInflater
import com.revolhope.presentation.library.extensions.toBold

class SelectorPillView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    attrStyleDef: Int = 0
) : ConstraintLayout(context, attributes, attrStyleDef) {

    private val binding =
        ComponentSelectorPillViewBinding.inflate(context.layoutInflater, this, true)
    private lateinit var model: SelectorPillUIModel

    fun bind(model: SelectorPillUIModel) {
        this.model = model
        binding.pillTextView.text = this.model.text
        setOnClickListener {
            this.model.isSelected = this.model.isSelected.not()
            updateBackground()
            updateTextStyle()
        }
        updateBackground()
        updateTextStyle()
    }

    private fun updateTextStyle() {
        binding.pillTextView.setTextColor(
            color(
                if (model.isSelected) {
                    R.color.primaryTextColorAccent
                } else {
                    R.color.primaryTextColor
                }
            )
        )
        binding.pillTextView.text = model.text.apply { if (model.isSelected) toBold() }
    }

    private fun updateBackground() {
        binding.rootLayout.background = drawable(
            if (model.isSelected) {
                R.drawable.pill_background_selected
            } else {
                R.drawable.pill_background
            }
        )
    }
}
