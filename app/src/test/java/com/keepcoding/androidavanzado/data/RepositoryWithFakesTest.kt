package com.keepcoding.androidavanzado.data

import com.google.common.truth.Truth
import com.keepcoding.androidavanzado.data.local.LocalDataSource
import com.keepcoding.androidavanzado.data.mappers.LocalToUIMapper
import com.keepcoding.androidavanzado.data.mappers.RemoteToUIMapper
import com.keepcoding.androidavanzado.data.remote.RemoteDataSource
import com.keepcoding.androidavanzado.domain.model.mapToLocal
import com.keepcoding.androidavanzado.utils.fakes.FakeLocalDataSource
import com.keepcoding.androidavanzado.utils.generateRemoteHeros
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepositoryWithFakesTest {

    // SUT
    private lateinit var repository: Repository

    // Dependencias
    private val localDataSource: FakeLocalDataSource = FakeLocalDataSource()
    private val remoteDataSource: RemoteDataSource = mockk()
    private val localToUIMapper: LocalToUIMapper = LocalToUIMapper()
    private val remoteToUIMapper: RemoteToUIMapper = RemoteToUIMapper()

    @Before
    fun setUp() {
        repository = Repository(localDataSource, remoteDataSource, localToUIMapper, remoteToUIMapper)
    }

    @Test
    fun `WHEN getHeroList and empty database THEN success list`() = runTest {
        // GIVEN
        val remoteHeros = generateRemoteHeros(20)
        val localHeros = remoteHeros.map { it.mapToLocal() }

        coEvery { remoteDataSource.getHeroList() } returns remoteHeros

        // WHEN
        val actual = repository.getHeroList()

        // THEN
        coVerify(exactly = 1) { remoteDataSource.getHeroList() }
        Truth.assertThat(actual).containsExactlyElementsIn(localToUIMapper.map(localHeros))
    }

}

