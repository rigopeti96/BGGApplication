package hu.bme.aut.android.brewdogapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {
    @Query("SELECT * from beer ORDER BY id ASC")
    fun getBeers(): List<Beer>

    @Query("SELECT * from beer WHERE id = :id")
    fun getBeer(id: String): Beer

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeer(newBeer: Beer)

    @Delete
    fun deleteBeer(beer: Beer)

    @Query("DELETE FROM beer")
    fun deleteAllBeer()
}