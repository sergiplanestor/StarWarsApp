package com.revolhope.presentation.library.components.selector.adapter.pill

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ComponentSelectorPillViewBinding
import com.revolhope.presentation.library.extensions.drawable
import com.revolhope.presentation.library.extensions.layoutInflater

class SelectorPillView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    attrStyleDef: Int = 0
) : ConstraintLayout(context, attributes, attrStyleDef) {

    private val binding = ComponentSelectorPillViewBinding.inflate(context.layoutInflater, this, true)
    private lateinit var model: SelectorPillUIModel

    fun bind(model: SelectorPillUIModel) {
        this.model = model
        binding.pillTextView.text = this.model.text
        setOnClickListener {
            this.model.isSelected = this.model.isSelected.not()
            updateBackground()
        }
        updateBackground()
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
