package com.evanyeomans.dmhelper.data

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evanyeomans.dmhelper.models.ItemModel
import com.evanyeomans.dmhelper.models.toModel
import kotlinx.coroutines.launch

 class ViewModelRoom(context: Context): ViewModelInterface, ViewModel() {
    val db = AppDatabase.getInstance(context)
    val itemRoomDAO = db.roomDao()
    var itemRepository = ItemRepository(itemRoomDAO)

    override var itemList: MutableList<ItemModel> = mutableStateListOf()
    override var searchResult: MutableList<ItemModel> = mutableStateListOf()

    init {
        getAll()
    }
    override fun getAll(){
        viewModelScope.launch {
            Log.d("itemviewmodel", "getall called")
            itemList.clear()
            itemRepository.getAll().collect(){
                response-> for (entity in response){
                    if(itemList.contains(entity.toModel())){
                        Log.d("itemviewmodel", "Duplicate found")
                    }
                    else{
                        Log.d("itemviewmodel", "adding item")
                        itemList.add(entity.toModel())
                    }
            }
            }
        }
    }

    override fun addItem(item: ItemModel) {
        viewModelScope.launch {
            itemRepository.addItem(item)
        }
    }

    override fun itemCount(): Int {
        return itemList.size
    }

    override fun getRandom(callback: (ItemModel?) -> Unit) {
        viewModelScope.launch {
            try {
                val entity = itemRepository.getRandom()
                entity?.let {
                    callback(it.toModel())
                } ?: callback(null) // Call the callback with null if entity is null
            } catch (e: Exception) {
                Log.e("ViewModelRoom", "Error getting random item: ${e.message}")
                callback(null)
            }
        }
    }

     override fun searchName(name: String) {
         viewModelScope.launch {
             try {
                 Log.d("searchname", "Searched")
                 searchResult.clear() // Clear the existing items list
                 itemRepository.searchName(name).collect { searchResults ->
                     searchResults.forEach { entity ->
                         //val model = entity.toModel() // Convert each search result to ItemModel
                         //if (!searchResult.contains(entity.toModel())) { // Check if the item is not already in the list
                             searchResult.add(entity.toModel()) // Add the item to the list
                            Log.d("search", searchResult.toString())
//                         } else {
//                             Log.d("ViewModelRoom", "Duplicate found")
//                             Log.d("viewmodelroom", entity.name)
//                         }
                     }
                 }
             } catch (e: Exception) {
                 Log.e("ViewModelRoom", "Error searching by name: ${e.message}")
             }
         }
     }


 }