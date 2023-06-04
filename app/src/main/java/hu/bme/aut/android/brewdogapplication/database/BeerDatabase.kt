package hu.bme.aut.android.brewdogapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Beer::class], version = 1, exportSchema = false)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun beerDao():BeerDao
}