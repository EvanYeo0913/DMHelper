package com.evanyeomans.dmhelper.models

data class ItemModel (
    val id: Int,
    val name: String,
    val description: String,
    val rarity: Rarity,
    val type: Type,
    val cost: Int
    )

//convert to entity
fun ItemModel.toEntity(): ItemEntity {
    return ItemEntity(id, name, description, cost, rarity, type)
}
