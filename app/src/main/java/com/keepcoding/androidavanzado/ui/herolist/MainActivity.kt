package com.keepcoding.androidavanzado.ui.herolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.androidavanzado.R
import com.keepcoding.androidavanzado.data.Repository
import com.keepcoding.androidavanzado.data.local.LocalDataSource
import com.keepcoding.androidavanzado.data.mappers.LocalToUIMapper
import com.keepcoding.androidavanzado.data.remote.DragonBallApi
import com.keepcoding.androidavanzado.data.remote.RemoteDataSource
import com.keepcoding.androidavanzado.example.CoroutineViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val adapter = HeroAdapter()

    private val viewModel: HeroListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.heros.observe(this) { heros ->
            adapter.addHeros(heros)
        }

        val heroList = findViewById<RecyclerView>(R.id.hero_list)

        val myButton = findViewById<Button>(R.id.my_button)
        myButton.setOnClickListener {
            viewModel.getHeroList()
        }

        heroList.adapter = adapter
        heroList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.notifyDataSetChanged()
    }
}
