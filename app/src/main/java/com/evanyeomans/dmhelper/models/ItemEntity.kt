package com.evanyeomans.dmhelper.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item_table")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "item_id") val id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "cost") var cost: Int,
    @ColumnInfo(name = "rarity") var rarity: Rarity,
    @ColumnInfo(name = "type") var type: Type,
)

fun ItemEntity.toModel():ItemModel{
    return ItemModel(id, name, description, rarity, type, cost)
}