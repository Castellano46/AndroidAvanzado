package com.keepcoding.androidavanzado.data

import com.keepcoding.androidavanzado.data.local.LocalDataSource
import com.keepcoding.androidavanzado.data.mappers.LocalToUIMapper
import com.keepcoding.androidavanzado.data.remote.RemoteDataSource
import com.keepcoding.androidavanzado.domain.model.mapToLocal
import com.keepcoding.androidavanzado.utils.generateLocalHeros
import com.keepcoding.androidavanzado.utils.generateRemoteHeros
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Test

class RepositoryWithMocksTest {

    // SUT
    private lateinit var repository: Repository

    // Dependencias
    private val localDataSource: LocalDataSource = mockk()
    private val remoteDataSource: RemoteDataSource = mockk()
    private val localToUIMapper: LocalToUIMapper = LocalToUIMapper()

    @Before
    fun setUp() {
        repository = Repository(localDataSource, remoteDataSource, localToUIMapper)
    }

    @Test
    fun `WHEN getHeroList and not empty database THEN success list`() = runTest {
        // GIVEN
        val localHeros = generateLocalHeros(20)
        every { localDataSource.getHeros() } returns localHeros

        // WHEN
        val actual = repository.getHeroList()

        // THEN
        Assert.assertTrue(actual.isNotEmpty())
    }

    @Test
    fun `WHEN getHeroList and empty database THEN success list`() = runTest {
        // GIVEN
        val remoteHeros = generateRemoteHeros(20)
        val localHeros = remoteHeros.map { it.mapToLocal() }

        every { localDataSource.getHeros() } returns emptyList()
        every { localDataSource.insertHeros(localHeros) } just runs
        coEvery { remoteDataSource.getHeroList() } returns remoteHeros

        // WHEN
        val actual = repository.getHeroList()

        // THEN
        verify(exactly = 2) { localDataSource.getHeros() }
        verify(exactly = 1) { localDataSource.insertHeros(localHeros) }
        coVerify(exactly = 1) { remoteDataSource.getHeroList() }
    }

    @After
    fun tearDown() {
    }
}
