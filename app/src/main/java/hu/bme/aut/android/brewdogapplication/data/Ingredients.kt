package hu.bme.aut.android.brewdogapplication.data

data class Ingredients(
    val malt: List<Malt>,
    val hops: List<Hops>,
    val yeast: String,
)
