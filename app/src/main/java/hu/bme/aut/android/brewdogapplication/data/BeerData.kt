package hu.bme.aut.android.brewdogapplication.data

data class BeerData(
    val id: Int,
    val name: String,
    val tagline: String,
    val abv: Double,
    val ibu: Int,
    val ingredients: List<Ingredients>,
    val yeast: String,
    val food_pairing: List<String>
)
