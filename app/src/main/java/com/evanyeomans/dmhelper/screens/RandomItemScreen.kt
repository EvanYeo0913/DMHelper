package com.evanyeomans.dmhelper.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.evanyeomans.dmhelper.data.ViewModelRoom
import com.evanyeomans.dmhelper.models.ItemModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomItemScreen(navController: NavController, viewModel: ViewModelRoom){
    var item by remember { mutableStateOf<ItemModel?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                isLoading = true // Set loading state to true
                viewModel.getRandom() { randomItem ->
                    item = randomItem
                    isLoading = false // Set loading state to false when query completes
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Generate")
        }

        // Show loading indicator if isLoading is true
        if (isLoading) {
            CircularProgressIndicator() // Add CircularProgressIndicator while loading
        } else {
            item?.let { generatedItem ->
                // Display the generated item UI
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Random Item", style = MaterialTheme.typography.titleMedium)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .border(1.dp, Color.Gray)
                ) {
                    Column(modifier = Modifier.padding(4.dp)) {
                        Text("Name: ${generatedItem.name}")
                        Text("Cost: ${generatedItem.cost.toString()}")
                        Text("Type: ${generatedItem.type.toString()}")
                        Text(text = generatedItem.rarity.toString())
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = generatedItem.description)
                    }
                }
            }
        }
    } // end column
} // end fun
