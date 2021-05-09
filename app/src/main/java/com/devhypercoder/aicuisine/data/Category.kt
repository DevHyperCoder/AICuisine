package com.devhypercoder.aicuisine.data

data class Category(
    val name: String,
    val icon: String,
    val color: String,
    val recipes: ArrayList<Recipe>
)

data class Recipe(val name: String, val ingredients: ArrayList<Ingredient>, val image: String)

data class Ingredient(val image: String, val amount: String, val name: String)

