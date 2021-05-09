package com.revolhope.presentation.feature.species.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.domain.common.extensions.UNKNOWN
import com.revolhope.domain.feature.search.model.SpecieModel
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ComponentSpecieViewBinding
import com.revolhope.presentation.library.extensions.layoutInflater

class SpecieView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding = ComponentSpecieViewBinding.inflate(context.layoutInflater, this, true)

    fun bind(model: SpecieModel) {
        binding.nameTextView.text = model.name
        binding.classificationTextView.text =
            context.getString(R.string.classification, model.classification)
        binding.designationTextView.text =
            context.getString(R.string.designation, model.designation)
        binding.languageTextView.text = context.getString(R.string.language, model.language)
        model.homeWorld?.let {
            binding.homeWorldTextView.text = context.getString(R.string.home_world, it)
        }
        binding.averageHeightTextView.text =
            context.getString(R.string.average_height, withUnitsOrUnknown(model.heightAverage, "cm"))
        binding.averageLifeSpanTextView.text =
            context.getString(R.string.average_life_span, withUnitsOrUnknown(model.lifeSpanAverage, "years"))
    }

    private fun withUnitsOrUnknown(text: String, units: String) =
        if (!text.equals(UNKNOWN, ignoreCase = true)) {
            "$text $units"
        } else {
            text
        }
}
