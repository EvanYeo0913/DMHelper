package com.evanyeomans.dmhelper.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.evanyeomans.dmhelper.data.ViewModelInterface
import com.evanyeomans.dmhelper.data.ViewModelRoom

@SuppressLint("RememberReturnType")
@Composable
fun ViewAllScreen(navController: NavController, viewModel: ViewModelRoom) {
    val itemList = viewModel.itemList
    var expandedIndex by remember { mutableStateOf(-1)}

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Header Row
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
        Column(modifier = Modifier.weight(1f).border(2.dp, Color.Gray)) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .border(2.dp, Color.Gray)
            ) {
                items(itemList.size) { index ->
                    val item = itemList.getOrNull(index)
                    item?.let {

                        // Display database item UI
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
                        } // end row

                            if (expandedIndex == index) {
                                Text(
                                    text = item.description,
                                    modifier = Modifier.weight(1f).padding(16.dp)
                                )
                            }



                    }
                }
            }
        }
    }
}
