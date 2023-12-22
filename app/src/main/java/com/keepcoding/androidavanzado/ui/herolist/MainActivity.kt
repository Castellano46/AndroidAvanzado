package com.keepcoding.androidavanzado.ui.herolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.androidavanzado.R
import com.keepcoding.androidavanzado.example.CoroutineViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val adapter = HeroAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = HeroListViewModel()

        viewModel.heros.observe(this) { heros ->
            val heroNames = heros.map { it.name }
            adapter.addHeros(heroNames)
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
