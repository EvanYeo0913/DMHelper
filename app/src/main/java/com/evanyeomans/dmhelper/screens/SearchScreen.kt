package com.evanyeomans.dmhelper.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.evanyeomans.dmhelper.data.ViewModelRoom
import com.evanyeomans.dmhelper.models.ItemModel

@Composable
fun SearchScreen(viewModel: ViewModelRoom) {
    var searchString by remember { mutableStateOf("") }
    var searchResult = viewModel.searchResult
    var expandedIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = searchString,
            onValueChange = { searchString = it },
            label = { Text("Item Name") }
        )
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = {
            viewModel.searchName(searchString)
        }) {
            Text("Search")
        }

        // Display search results
        Column(
            modifier = Modifier
                .weight(1f)
                .border(2.dp, Color.Gray)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Name", modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium)
                Text(text = "Cost", modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium)
                Text(text = "Type", modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium)
                Text(text = "Rarity", modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium)
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)

            ) {
                items(searchResult) { item ->
                    val index = searchResult.indexOf(item)
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .clickable {
                                expandedIndex = if (expandedIndex == index) -1 else index
                            }
                    ) {
                        Text(
                            text = item.name,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = item.cost.toString(),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = item.type.toString(),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = item.rarity.toString(),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (expandedIndex == index) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = item.description,
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

