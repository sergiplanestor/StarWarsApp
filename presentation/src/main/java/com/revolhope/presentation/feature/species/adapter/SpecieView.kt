package com.revolhope.presentation.feature.species.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.domain.feature.search.model.SpecieModel
import com.revolhope.mylibra.databinding.ComponentSpecieViewBinding
import com.revolhope.presentation.library.extensions.layoutInflater

class SpecieView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding = ComponentSpecieViewBinding.inflate(context.layoutInflater, this, true)

    fun bind(model: SpecieModel) {
        binding.titleTextView.text = model.name
    }

}
