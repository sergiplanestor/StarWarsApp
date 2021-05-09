package com.revolhope.presentation.feature.planets.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.domain.common.extensions.UNKNOWN
import com.revolhope.domain.feature.search.model.PlanetModel
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ComponentPlanetViewBinding
import com.revolhope.presentation.library.extensions.layoutInflater

class PlanetView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding = ComponentPlanetViewBinding.inflate(context.layoutInflater, this, true)

    fun bind(model: PlanetModel) {
        binding.nameTextView.text = model.name
        binding.diameterTextView.text =
            context.getString(R.string.diameter, withUnitsOrUnknown(model.diameter, "Km"))
        binding.orbitalPeriodTextView.text =
            context.getString(R.string.orbital_period, withUnitsOrUnknown(model.orbitalPeriod, "days"))
        binding.rotationPeriodTextView.text =
            context.getString(R.string.rotation_period, withUnitsOrUnknown(model.rotationPeriod, "hours"))

        binding.populationTextView.text =
            context.getString(R.string.population, model.population)
        binding.terrainTextView.text =
            context.getString(R.string.terrain, model.terrain)
        binding.climateTextView.text =
            context.getString(R.string.climate, model.climate)
    }

    private fun withUnitsOrUnknown(text: String, units: String) =
        if (!text.equals(UNKNOWN, ignoreCase = true)) {
            "$text $units"
        } else {
            text
        }

}
