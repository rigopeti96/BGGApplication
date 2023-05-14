package hu.bme.aut.android.bggapplication.data

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "items", strict = false)
data class CollectiolListByOwnerName(
    @field:ElementList(name = "item", inline = true, required = false)
    @param:ElementList(name = "item", inline = true, required = false)
    @field:Path("items")
    @param:Path("items")
    var items: List<CollectionListItem>? = null
)
