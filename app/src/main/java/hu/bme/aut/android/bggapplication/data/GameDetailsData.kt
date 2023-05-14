package hu.bme.aut.android.bggapplication.data

import org.simpleframework.xml.Root
import java.util.*

data class GameDetailsData(
    val title: String,
    val publisher: String,
    val publishYear: Date
)
