package com.evanyeomans.dmhelper.data

import com.evanyeomans.dmhelper.models.ItemModel
import com.evanyeomans.dmhelper.models.toEntity
import com.evanyeomans.dmhelper.models.toModel

class ItemRepository(val roomDAO: RoomDAO) {
    suspend fun getAll() = roomDAO.getAll()
    suspend fun addItem(item: ItemModel) = roomDAO.addItem(item.toEntity())
    suspend fun getRandom() = roomDAO.getRandom()
    suspend fun searchName(name: String) = roomDAO.searchName(name)
}