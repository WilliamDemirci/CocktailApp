package com.williamdemirci.cocktailapi.view

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.williamdemirci.cocktailapi.R
import com.williamdemirci.cocktailapi.data.CocktailData

class CocktailAdapter(private val drinks: ArrayList<CocktailData>) :
    RecyclerView.Adapter<CocktailAdapter.ViewHolder>() {

    var drinksFilteredList = ArrayList<CocktailData>()

    init {
        drinksFilteredList = drinks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cocktail, parent, false)
        )
    }

    override fun getItemCount() = drinks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cocktail = drinks[position]
        holder.bind(cocktail)
        holder.itemView.setOnClickListener {
            // Do something when click on item
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val picture: ImageView = itemView.findViewById(R.id.picture)
        private val cocktailName: TextView = itemView.findViewById(R.id.cocktailName)
        private val showDescriptionButton: TextView = itemView.findViewById(R.id.showDescriptionButton)
        private val description: TextView = itemView.findViewById(R.id.description)

        fun bind(cocktail: CocktailData) {
            Picasso.get().load(cocktail.strDrinkThumb).into(picture)
            cocktailName.text = cocktail.strDrink
            description.text = cocktail.strIngredient1 + " + " + cocktail.strIngredient2 + " + " + cocktail.strIngredient3
            showDescriptionButton.setOnClickListener {
                description.visibility = VISIBLE
            }
        }
    }
}
