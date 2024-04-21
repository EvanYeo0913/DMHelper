package com.evanyeomans.dmhelper.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.evanyeomans.dmhelper.models.ItemModel
import com.evanyeomans.dmhelper.models.Rarity

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun RandomItemScreen(navController: NavController){
    var isExpanded by remember{mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // First Row: Two Text Fields
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = "",
                onValueChange = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                label = { Text("Min Cost") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            TextField(
                value = "",
                onValueChange = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                label = { Text("Max Cost") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }
        Spacer(modifier = Modifier.height(80.dp))

            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = it }, // Directly assign the new value
                modifier = Modifier.fillMaxWidth() // Add modifier for width
            ) {
                Column {
                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false }, // Directly assign false
                        modifier = Modifier.fillMaxWidth() // Add modifier for width
                    ) {
                        // Dropdown Menu Items
                        DropdownMenuItem(onClick = { /*TODO*/ }, text = {Text(text = "Item")})
                    }
                }
            }

        Button(onClick={}, modifier = Modifier.padding(16.dp)){
            Text("Generate")
        }

    }

}