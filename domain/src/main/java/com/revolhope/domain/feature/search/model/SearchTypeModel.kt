package com.revolhope.domain.feature.searchtype.model

sealed class SearchTypeModel(
    open val id: Int,
    open var text: String?
) {

    data class Episode(override var text: String? = null) :
        SearchTypeModel(EPISODES_ID, text)

    data class Characters(override var text: String? = null) :
        SearchTypeModel(CHARACTERS_ID, text)

    data class Planets(override var text: String? = null) :
        SearchTypeModel(PLANETS_ID, text)

    data class Species(override var text: String? = null) :
        SearchTypeModel(SPECIES_ID, text)

    object Unknown : SearchTypeModel(UNKNOWN_ID, null)

    companion object {

        private const val EPISODES_ID = 0
        private const val CHARACTERS_ID = 1
        private const val PLANETS_ID = 2
        private const val SPECIES_ID = 3
        private const val UNKNOWN_ID = 3

        fun fromId(id: Int): SearchTypeModel =
            when (id) {
                EPISODES_ID -> Episode()
                CHARACTERS_ID -> Characters()
                PLANETS_ID -> Planets()
                SPECIES_ID -> Species()
                else -> Unknown
            }

        val values: List<SearchTypeModel>
            get() = listOf(
                Episode(),
                Characters(),
                Planets(),
                Species()
            )

        val valuesById: Map<Int, SearchTypeModel>
            get() = values.associateBy { it.id }
    }
}
