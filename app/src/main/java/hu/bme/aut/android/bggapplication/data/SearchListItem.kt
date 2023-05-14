package hu.bme.aut.android.bggapplication.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "boardgame", strict = false)
data class SearchListItem @JvmOverloads constructor(
    @field:Element(name = "name")
    @param:Element(name = "name")
    var name: String? = null,

    @field:Element(name = "yearpublished")
    @param:Element(name = "yearpublished")
    var yearpublished: Int? = null
)