package com.williamdemirci.cocktailapi.data

data class Drinks(
    val drinks: ArrayList<CocktailData>,
)

data class CocktailData(
    val idDrink: String,
    val strDrink: String?,
    val strAlcoholic: String?,
    val strGlass: String?,
    val strDrinkThumb: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
)
