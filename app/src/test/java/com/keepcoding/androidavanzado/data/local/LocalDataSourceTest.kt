package com.keepcoding.androidavanzado.data.local

import com.google.common.truth.Truth
import com.keepcoding.androidavanzado.domain.model.HeroLocal
import com.keepcoding.androidavanzado.utils.generateLocalHeros
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {

    // SUT
    private lateinit var localDataSource: LocalDataSource

    // Dependencias
    private val dao: HeroDAO = mockk()

    @Before
    fun setUp() {
        localDataSource = LocalDataSource(dao)
    }

    @Test
    fun `WHEN getHeros THEN success list`() {
        // Given
        val expected = generateLocalHeros(20)
        every { dao.getAll() } returns expected

        // When
        val actual = localDataSource.getHeros()

        // Then
        Assert.assertTrue(actual.isNotEmpty())
        Assert.assertEquals(true, actual.isNotEmpty())
        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @After
    fun tearDown() {

    }
}
