package com.keepcoding.androidavanzado.ui.herolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.utils.generateRemoteHeros
import com.keepcoding.androidavanzado.utils.generateUIHeros
import com.keepcoding.androidavanzado.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HeroListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // SUT
    private lateinit var viewModel: HeroListViewModel

    // Depencias
    private val repository: Repository = mockk()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        viewModel = HeroListViewModel(repository, UnconfinedTestDispatcher())
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `WHEN getHeroList THEN return Loading state and success list`() = runTest {
        coEvery { repository.getHeroList() } returns generateUIHeros(20)

        val actual = viewModel.getHeroList()

        val actualLiveDataValue1 = viewModel.heros.getOrAwaitValue()
        Truth.assertThat(actualLiveDataValue1).isEqualTo(HeroListState.Loading)
        val actualLiveDataValue2 = viewModel.heros.getOrAwaitValue()
        Truth.assertThat(actualLiveDataValue2).isEqualTo(HeroListState.Success(generateUIHeros(20)))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
