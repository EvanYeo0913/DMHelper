package com.evanyeomans.dmhelper.data

import com.evanyeomans.dmhelper.models.ItemModel

interface ViewModelInterface {

    var itemList: MutableList<ItemModel>

    fun getAll()
    fun addItem(item: ItemModel)
    fun itemCount():Int
}