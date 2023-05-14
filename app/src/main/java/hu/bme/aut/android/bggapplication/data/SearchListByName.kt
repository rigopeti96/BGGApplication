package hu.bme.aut.android.bggapplication.data

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "boardgames", strict = false)
data class SearchListByName @JvmOverloads constructor(
    @field:ElementList(name = "boardgame", inline = true, required = false)
    @param:ElementList(name = "boardgame", inline = true, required = false)
    @field:Path("boardgames")
    @param:Path("boardgames")
    var boardgames: List<SearchListItem>? = null
)