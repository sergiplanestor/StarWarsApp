package com.revolhope.presentation.library.components.searchview

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.revolhope.domain.feature.searchtype.model.SearchTypeModel
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ComponentSearchViewBinding
import com.revolhope.presentation.library.components.selector.SelectorUIModel
import com.revolhope.presentation.library.components.selector.adapter.pill.SelectorPillUIModel
import com.revolhope.presentation.library.extensions.input
import com.revolhope.presentation.library.extensions.layoutInflater
import com.revolhope.presentation.library.extensions.lowerCase

class SearchView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    attrStyleDef: Int = 0
) : ConstraintLayout(context, attributes, attrStyleDef) {

    private val binding = ComponentSearchViewBinding.inflate(context.layoutInflater, this, true)

    /*EPISODES(0, R.string.episodes),
        CHARACTERS(1, R.string.characters),
        PLANETS(2, R.string.planets),
        SPECIES(3, R.string.species),
        UNKNOWN(-1, -1);*/

    // Public properties
    var searchType: SearchTypeModel = SearchTypeModel.Planets()
        set(value) {
            field = value
            bindSelector()
            bindEditText()
        }
    var onQuerySummit: ((type: SearchTypeModel, input: String) -> Unit)? = null

    // Private properties
    private var isFilterLayoutVisible: Boolean = false
    private val filters: List<SelectorPillUIModel>
        get() = SearchTypeModel.values.map {
            it.fillText()
            SelectorPillUIModel(
                it.id,
                it.text.orEmpty(),
                isSelected = it == searchType
            )
        }
    private var inputText: String
        get() = binding.searchEditText.input.orEmpty()
        set(value) {
            binding.searchEditText.setText(value)
        }
    private val isFieldValid: Boolean get() = inputText.isBlank().not().also(::changeInputErrorState)


    init {
        bindSelector()
        bindEditText()
        setupListeners()
    }

    private fun bindSelector() {
        binding.filterSelectorView.bind(SelectorUIModel(filters, ::onSearchTypeChanged))
    }

    private fun bindEditText() {
        binding.searchEditText.hint = context.getString(
            R.string.search_for,
            searchType.text?.lowerCase.orEmpty()
        )
    }

    private fun setupListeners() {
        // End icon action
        binding.searchInputLayout.setEndIconOnClickListener {
            if (isFieldValid) onQuerySummit?.invoke(searchType, inputText)
        }
        // Edit Text IME action
        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (isFieldValid) onQuerySummit?.invoke(searchType, inputText)
                true
            } else {
                false
            }
        }
        // Filters button action
        binding.filtersButton.setOnClickListener {
            isFilterLayoutVisible = isFilterLayoutVisible.not()
            binding.filterSelectorView.isVisible = isFilterLayoutVisible
        }
    }

    private fun onSearchTypeChanged(searchTypeId: Int) {
        SearchTypeModel.valuesById[searchTypeId].takeIf { it !is SearchTypeModel.Unknown }?.let {
            it.fillText()
            searchType = it
        }
    }

    private fun changeInputErrorState(isValid: Boolean) {
        binding.searchInputLayout.error = if(isValid) null else context.getString(R.string.search_query_invalid)
    }

    private fun SearchTypeModel.fillText() {
        text = when (this) {
            is SearchTypeModel.Characters -> R.string.characters
            is SearchTypeModel.Episode -> R.string.episodes
            is SearchTypeModel.Planets -> R.string.planets
            is SearchTypeModel.Species -> R.string.species
            SearchTypeModel.Unknown -> null
        }?.let(context::getString)
    }
}
