package com.keepcoding.androidavanzado.ui.herolist
//jfdg
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.androidavanzado.R

class HeroAdapter : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    private val heros = mutableListOf(
        "Goku",
        "Vegeta",
        "Krilin",
        "Picolo",
        "Goku",
        "Vegeta",
        "Krilin",
        "Picolo",
        "Goku",
        "Vegeta",
        "Krilin",
        "Picolo",
        "Goku",
        "Vegeta",
        "Krilin",
        "Picolo",
        "Goku",
        "Vegeta",
        "Krilin",
        "Picolo"
    )

    class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val heroName = itemView.findViewById<TextView>(R.id.hero_name)

        fun bind(name: String){
            heroName.text = name
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

    fun addHeros(heros: List<String>){
        this.heros.clear()
        this.heros.addAll(heros)
        notifyDataSetChanged()
    }
}



