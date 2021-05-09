package com.revolhope.presentation.feature.characters.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.domain.common.extensions.UNKNOWN
import com.revolhope.domain.feature.search.model.CharacterModel
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ComponentCharacterViewBinding
import com.revolhope.presentation.library.extensions.layoutInflater

class CharacterView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding = ComponentCharacterViewBinding.inflate(context.layoutInflater, this, true)

    fun bind(model: CharacterModel) {
        binding.nameTextView.text = model.name
        binding.birthYearTextView.text = context.getString(R.string.birth_year, model.birthYear)
        binding.genderTextView.text = context.getString(R.string.gender, model.gender)
        binding.heightTextView.text =
            context.getString(R.string.height, withUnitsOrUnknown(model.height, "cm"))
        binding.massTextView.text =
            context.getString(R.string.mass, withUnitsOrUnknown(model.weight, "Kg"))
        model.homeWorld?.let {
            binding.homeWorldTextView.text = context.getString(R.string.home_world, it)
        }
    }

    private fun withUnitsOrUnknown(text: String, units: String) =
        if (!text.equals(UNKNOWN, ignoreCase = true)) {
            "$text $units"
        } else {
            text
        }
}
