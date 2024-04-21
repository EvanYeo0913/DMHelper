package com.evanyeomans.dmhelper.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.evanyeomans.dmhelper.data.ViewModelInterface
import com.evanyeomans.dmhelper.data.ViewModelRoom
import com.evanyeomans.dmhelper.models.ItemEntity
import com.evanyeomans.dmhelper.models.Rarity
import com.evanyeomans.dmhelper.models.Type
import com.evanyeomans.dmhelper.models.toModel
import java.sql.Types.NULL

@Composable
fun AddItemScreen(navController: NavController, viewModel: ViewModelRoom) {
    val types = Type.entries.toTypedArray()
    val rarities = Rarity.entries.toTypedArray()

    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var selectedIndex1 by remember { mutableStateOf(0) }
    var selectedType by remember { mutableStateOf(types[selectedIndex1])}
    var selectedIndex2 by remember { mutableStateOf(0) }
    var selectedRarity by remember { mutableStateOf((rarities[selectedIndex2]))}
    var cost by remember { mutableStateOf("")}
    var name by remember { mutableStateOf("")}
    var description by remember { mutableStateOf("")}

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Add Item", style = MaterialTheme.typography.titleMedium , textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = name,
            onValueChange = {name = it}, label = {Text("Item Name")},
            modifier = Modifier.fillMaxWidth())
         Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = description,
            onValueChange = {description = it}, label = {Text("Item Description")},
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp))
        Spacer(modifier = Modifier.height(16.dp))

        // type dropdown
        Box(modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black),
            contentAlignment = Alignment.CenterStart) {
            TextButton(onClick = { expanded1 = true }, Modifier.fillMaxWidth()) {
                Text(selectedType.name)
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            DropdownMenu(
                expanded = expanded1,
                onDismissRequest = { expanded1 = false },

                ) {
                types.forEachIndexed { index, item ->
                    DropdownMenuItem(onClick = {
                        selectedIndex1 = index
                        selectedType = item
                        expanded1 = false
                    }, text = { Text(item.name) })
                }
            } // end drop down 1
        }


            Spacer(modifier = Modifier.height(16.dp))

        // rarity dropdown
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black),
                contentAlignment = Alignment.CenterStart
            ) {
                TextButton(onClick = { expanded2 = true }, Modifier.fillMaxWidth()) {
                    Text(selectedRarity.name)
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                DropdownMenu(
                    expanded = expanded2,
                    onDismissRequest = { expanded2 = false },

                    ) {
                    rarities.forEachIndexed { index, item ->
                        DropdownMenuItem(onClick = {
                            selectedIndex1 = index
                            selectedRarity = item
                            expanded2 = false
                        }, text = { Text(item.name) })
                    }
                }
            }

        Spacer(modifier = Modifier.height(16.dp))

        // Cost entry.
        OutlinedTextField(value = cost,
            onValueChange = {cost = it},
            label = {Text("Cost")},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        
        Spacer(modifier = Modifier.height(25.dp))
        
        Button(onClick = {
            var item = ItemEntity(
                NULL,
                name,
                description,
                cost.toInt(),
                selectedRarity,
                selectedType
            )
            viewModel.addItem(item.toModel())
        })
        {
            Text("Submit")
        }
        } // end column

} // end fun

