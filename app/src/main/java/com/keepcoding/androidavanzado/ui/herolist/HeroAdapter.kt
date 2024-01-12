package com.keepcoding.androidavanzado.ui.herolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keepcoding.androidavanzado.R
import com.keepcoding.androidavanzado.domain.model.HeroUI

class HeroAdapter(private val onClick: (HeroUI) -> Unit) : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    private val heros = mutableListOf<HeroUI>()

    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var heroUI: HeroUI

        private val heroName = itemView.findViewById<TextView>(R.id.hero_name)
        private val heroPhoto = itemView.findViewById<ImageView>(R.id.hero_photo)

        init {
            itemView.setOnClickListener {
                onClick(heroUI)
            }
        }

        fun bind(heroUI: HeroUI) {
            this.heroUI = heroUI

            heroName.text = heroUI.name
            heroPhoto.load(heroUI.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
        return HeroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return heros.count()
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(heros[position])
    }

    fun addHeros(heros: List<HeroUI>) {
        this.heros.clear()
        this.heros.addAll(heros)
        notifyDataSetChanged()
    }
}



