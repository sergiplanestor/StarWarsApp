package com.revolhope.presentation.feature.dashboard.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.revolhope.domain.feature.film.model.FilmModel
import com.revolhope.mylibra.databinding.ComponentFilmViewBinding
import com.revolhope.presentation.library.extensions.layoutInflater

class FilmView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding = ComponentFilmViewBinding.inflate(context.layoutInflater, this, true)

    fun bind(model: FilmModel) {
        binding.episodeIdTextView.text = model.episodeNum.toString()
        binding.titleTextView.text = model.title
        binding.directorTextView.text = model.director
    }

}
