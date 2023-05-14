package hu.bme.aut.android.bggapplication.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class CollectionListItem @JvmOverloads constructor(
    @field:Element(name = "name")
    @param:Element(name = "name")
    var name: String? = null,

    @field:Element(name = "yearpublished")
    @param:Element(name = "yearpublished")
    var yearpublished: Int? = null,

    @field:Element(name = "status")
    @param:Element(name = "status")
    var status: String? = null,

    @field:Element(name = "numplays")
    @param:Element(name = "numplays")
    var numplays: Int? = null,
)