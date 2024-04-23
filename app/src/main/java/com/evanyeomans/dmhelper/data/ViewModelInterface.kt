package com.evanyeomans.dmhelper.data

import com.evanyeomans.dmhelper.models.ItemModel

interface ViewModelInterface {

    var itemList: MutableList<ItemModel>
    var searchResult: MutableList<ItemModel>

    fun getAll()
    fun addItem(item: ItemModel)
    fun itemCount():Int
    fun getRandom(callback: (ItemModel?) -> Unit)
    fun searchName(name: String)
}