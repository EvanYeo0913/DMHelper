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
    var searchResult: MutableList<ItemModel> = mutableStateListOf()

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
}