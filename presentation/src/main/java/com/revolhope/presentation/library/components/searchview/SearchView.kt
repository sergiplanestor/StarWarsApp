package com.revolhope.presentation.library.components.searchview

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.mylibra.databinding.ComponentSearchViewBinding
import com.revolhope.presentation.library.extensions.layoutInflater

class SearchView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    attrStyleDef: Int = 0
) : ConstraintLayout(context, attributes, attrStyleDef) {

    private val binding = ComponentSearchViewBinding.inflate(context.layoutInflater, this, true)

}
