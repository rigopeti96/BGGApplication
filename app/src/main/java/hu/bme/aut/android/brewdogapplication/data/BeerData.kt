package hu.bme.aut.android.brewdogapplication.data

import java.io.FileDescriptor

data class BeerData(
    val id: Int,
    val name: String,
    val description: String,
    val tagline: String,
    val abv: Double,
    val ibu: Int,
    val ingredients: Ingredients,
    val food_pairing: List<String>
)
