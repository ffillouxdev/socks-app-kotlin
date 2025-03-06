package com.example.fillouxproject.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SubmitButton(
    onValidate: () -> Unit
) {
    Button(
        onClick = onValidate,
        modifier = Modifier.wrapContentSize()
    ) {
        Text(text = "Valider")
    }
}