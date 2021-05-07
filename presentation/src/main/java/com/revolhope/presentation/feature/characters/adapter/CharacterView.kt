package com.revolhope.presentation.feature.characters.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.domain.feature.search.model.CharacterModel
import com.revolhope.mylibra.databinding.ComponentFilmViewBinding
import com.revolhope.presentation.library.extensions.layoutInflater

class CharacterView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding = ComponentFilmViewBinding.inflate(context.layoutInflater, this, true)

    fun bind(model: CharacterModel) {
        binding.titleTextView.text = model.name
    }

}
