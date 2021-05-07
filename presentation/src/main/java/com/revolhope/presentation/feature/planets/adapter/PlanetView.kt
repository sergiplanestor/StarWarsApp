package com.revolhope.presentation.feature.planets.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.domain.feature.search.model.PlanetModel
import com.revolhope.mylibra.databinding.ComponentPlanetViewBinding
import com.revolhope.presentation.library.extensions.layoutInflater

class PlanetView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding = ComponentPlanetViewBinding.inflate(context.layoutInflater, this, true)

    fun bind(model: PlanetModel) {
        binding.titleTextView.text = model.name
    }

}
