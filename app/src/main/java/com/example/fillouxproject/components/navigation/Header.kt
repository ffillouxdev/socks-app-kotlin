package com.example.fillouxproject.components.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fillouxproject.R

@Composable
fun Header() {
    val picture = painterResource(R.drawable.logo_quickshop)
    Row {
        Image(painter=picture, contentDescription = null, modifier = Modifier.padding(24.dp))
        Text("QuickShop", color = Color.Black)
    }
}