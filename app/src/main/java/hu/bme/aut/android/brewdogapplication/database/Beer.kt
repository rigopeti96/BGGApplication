package hu.bme.aut.android.brewdogapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.bme.aut.android.brewdogapplication.data.Ingredients

@Entity(tableName = "beer")
data class Beer(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "tagline") val tagline: String,
    @ColumnInfo(name = "abv") val abv: Double,
    @ColumnInfo(name = "ibu") val ibu: Int,
    @ColumnInfo(name = "ingredients") val ingredients: Ingredients,
    @ColumnInfo(name = "food_pairing") val food_pairing: List<String>
)