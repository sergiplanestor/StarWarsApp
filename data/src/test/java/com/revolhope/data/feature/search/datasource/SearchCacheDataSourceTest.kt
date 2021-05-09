package com.revolhope.data.feature.search.datasource

import com.revolhope.data.feature.search.response.CharacterResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchCacheDataSourceTest {

    private val characters: List<CharacterResponse> = listOf(
        CharacterResponse(
            name = "character1",
            height = "123",
            weight = "66",
            hairColor = "",
            skinColor = "",
            eyeColor = "",
            birthYear = "",
            gender = "",
            homeWorld = "",
            species = null,
            vehicles = null,
            starships = null,
            createdOn = "",
            editedOn = "",
            url = ""
        ),
        CharacterResponse(
            name = "character1",
            height = "123",
            weight = "66",
            hairColor = "",
            skinColor = "",
            eyeColor = "",
            birthYear = "",
            gender = "",
            homeWorld = "",
            species = null,
            vehicles = null,
            starships = null,
            createdOn = "",
            editedOn = "",
            url = ""
        ),
        CharacterResponse(
            name = "character1",
            height = "123",
            weight = "66",
            hairColor = "",
            skinColor = "",
            eyeColor = "",
            birthYear = "",
            gender = "",
            homeWorld = "",
            species = null,
            vehicles = null,
            starships = null,
            createdOn = "",
            editedOn = "",
            url = ""
        )
    )

    @Test
    fun `insert characters into cache`() {
        val expected1 = true
        val expected2 = false
        val expected3 = 3
        assertEquals(expected1, SearchCacheDataSource.characters.isEmpty())
        SearchCacheDataSource.insertCharacters(characters)
        assertEquals(expected2, SearchCacheDataSource.characters.isEmpty())
        assertEquals(expected3, SearchCacheDataSource.characters.count())
    }

    @Test
    fun `clear character cache`() {
        val expected1 = false
        val expected2 = true
        SearchCacheDataSource.insertCharacters(characters)
        assertEquals(expected1, SearchCacheDataSource.characters.isEmpty())
        SearchCacheDataSource.clearCache()
        assertEquals(expected2, SearchCacheDataSource.characters.isEmpty())
    }
}
