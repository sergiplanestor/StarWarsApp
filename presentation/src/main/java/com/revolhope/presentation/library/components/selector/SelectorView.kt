package com.revolhope.presentation.library.components.selector

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.mylibra.databinding.ComponentSelectorViewBinding
import com.revolhope.presentation.library.components.selector.adapter.SelectorPillAdapter
import com.revolhope.presentation.library.components.selector.adapter.SelectorPillItemDecorator
import com.revolhope.presentation.library.extensions.layoutInflater

class SelectorView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    attrStyleDef: Int = 0
) : ConstraintLayout(context, attributes, attrStyleDef) {

    private val binding = ComponentSelectorViewBinding.inflate(context.layoutInflater, this, true)

    fun bind(model: SelectorUIModel) {
        with(binding.selectorRecyclerView) {
            adapter = SelectorPillAdapter(model.items, model.onPillSelected)
            if (itemDecorationCount == 0) addItemDecoration(SelectorPillItemDecorator())
        }
    }
}
