package com.keepcoding.androidavanzado.data.remote

import com.keepcoding.androidavanzado.utils.MockWebDispatcher
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteDataSourceTest {

    // SUT o UUT
    //private val remoteDataSource = RemoteDataSource()

    // Dependencias
    private lateinit var api: DragonBallApi


    @Before
    fun setUp(){
        val mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockWebDispatcher()
        mockWebServer.start()
        val loggerInterceptor =  HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggerInterceptor).build()

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        api = Retrofit.Builder().client(okHttpClient).baseUrl(mockWebServer.url("/")).addConverterFactory(
            MoshiConverterFactory.create(moshi)).build().create(DragonBallApi::class.java)
    }


    @Test
    fun `WHEN getHeroList THEN success list`() = runTest{
        // GIVEN
        val remoteDataSource = RemoteDataSource(api)

        // WHEN
        val heroList = remoteDataSource.getHeroList()

        // THEN
        Assert.assertEquals(true, heroList.isNotEmpty())
    }

    @After
    fun tearDown(){

    }

}
