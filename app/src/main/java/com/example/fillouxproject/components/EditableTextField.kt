package com.example.fillouxproject.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun EditableTextField(label: String, initialValue: String = "", onValueChange: (String) -> Unit) {
    var value by remember { mutableStateOf(initialValue) }

    TextField(
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        label = { Text(label) }
    )
}
