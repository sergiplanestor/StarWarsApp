package com.revolhope.presentation.library.components.selector

import com.revolhope.presentation.library.components.selector.adapter.pill.SelectorPillUIModel

data class SelectorUIModel(
    val items: List<SelectorPillUIModel>,
    val onPillSelected: (id: Int) -> Unit
)
