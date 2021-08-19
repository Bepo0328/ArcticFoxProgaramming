package kr.co.bepo.arcticfoxprogaramming.placeholder

import java.util.*

object PlaceholderContent {

    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    init {
        addItem(
            PlaceholderItem("1", "eBookFrenzy",
            "https://www.ebookfrenzy.com")
        )
        addItem(PlaceholderItem("2", "Amazon",
            "https://www.amazon.com"))
        addItem(PlaceholderItem("3", "New York Times",
            "https://www.nytimes.com"))
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    data class PlaceholderItem(val id: String, val website_name: String, val website_url: String) {
        override fun toString(): String = website_name
    }
}