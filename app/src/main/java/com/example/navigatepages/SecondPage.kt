package com.example.navigatepages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle

@Composable
fun secondPage(name : String, modifier: Modifier, goToFirstPage : (String, Int) -> Unit) {

    val input = remember { mutableStateOf("") }

    Column (modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Name is $name, provide age")
        TextField(value = input.value, onValueChange = {
            input.value = it
        })
        Button(onClick = { goToFirstPage(name, input.value.toInt()) }) {
            Text(text = "Third Page")
        }
    }

}