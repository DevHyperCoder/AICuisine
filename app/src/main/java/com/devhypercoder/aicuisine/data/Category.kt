package com.devhypercoder.aicuisine.data

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val TAG = "FIREBASE"

data class Category(
    val name: String,
    val icon: String,
    val color: String,
    val recipes: ArrayList<Recipe>
)

data class Recipe(val name: String, val ingredients: ArrayList<Ingredient>, val image: String)

data class Ingredient(val image: String, val amount: String, val name: String)

fun getRecipe(recipie: Recipe, callback: (recipe: Recipe) -> Unit) {
    val name = recipie.name

    val database = Firebase.firestore
    database.document("/recipes/$name").get().addOnSuccessListener {
        val data = it.data!!

        val image = data["image"] as String

        val ingredientList = data["ingredients"] as ArrayList<HashMap<String, String>>
        val ingredients = ArrayList<Ingredient>()

        for (ingredientData in ingredientList) {
            val ingredient = Ingredient(
                name = ingredientData["name"]!!,
                amount = ingredientData["amount"]!!,
                image = ingredientData["image"]!!
            )
            ingredients.add(ingredient)
        }

        callback(
            Recipe(
                name,
                ingredients,
                image
            )
        )

    }
}

fun getCategoryListFB(callback: (categories: ArrayList<Category>) -> Unit) {
    val database = Firebase.firestore

    val categories = database.collection("/categories/").get()
    categories.addOnSuccessListener {
        Log.d(TAG, "getCategoryListFB: ")
        val categoryList = ArrayList<Category>()
        for (i in it.documents) {
            val docData = i.data!!
            val categoryColor = docData["color"] as String
            val categoryImage = docData["image"] as String
            val categoryName = i.id

            val recipesList = docData["recipes"] as ArrayList<String>
            val recipes = ArrayList<Recipe>()
            for (recipe in recipesList) {
                recipes.add(
                    Recipe(name = recipe, ArrayList(), "")
                )
            }

            val category = Category(
                name = categoryName,
                color = categoryColor,
                icon = categoryImage,
                recipes = recipes
            )
            categoryList.add(category)
        }
        callback(categoryList)
    }
}

