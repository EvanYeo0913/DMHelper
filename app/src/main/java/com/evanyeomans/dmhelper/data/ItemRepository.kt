package com.evanyeomans.dmhelper.data

import com.evanyeomans.dmhelper.models.ItemModel
import com.evanyeomans.dmhelper.models.toEntity

class ItemRepository(val roomDAO: RoomDAO) {
    suspend fun getAll() = roomDAO.getAll()
    suspend fun addItem(item: ItemModel) = roomDAO.addItem(item.toEntity())
}