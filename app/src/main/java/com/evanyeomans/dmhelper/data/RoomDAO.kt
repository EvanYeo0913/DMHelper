package com.evanyeomans.dmhelper.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evanyeomans.dmhelper.models.ItemEntity
import kotlinx.coroutines.flow.Flow

// Data access object
@Dao
interface RoomDAO {
    // add item to database, return if added, null if failed
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: ItemEntity)

    @Query("SELECT * FROM item_table")
    fun getAll(): Flow<List<ItemEntity>>

    @Query("SELECT * FROM item_table ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandom(): ItemEntity

    @Query("SELECT * FROM item_table WHERE name LIKE '%' || :name || '%'")
     fun searchName(name: String): Flow<List<ItemEntity>>
}