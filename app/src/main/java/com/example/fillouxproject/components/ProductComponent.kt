package com.example.fillouxproject.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fillouxproject.R
import com.example.fillouxproject.datas.Product

@Composable
fun ProductComponent(prod: Product, handleDelete: () -> Unit) {
    val picture = painterResource(R.drawable.socks1)
    val context = LocalContext.current

    Row(modifier = Modifier
        .padding(16.dp)
        .pointerInput(Unit) {
            detectTapGestures(
                onLongPress = {
                    handleDelete()
                },
                onTap = {
                    Toast.makeText(context, "Product: ${prod.name} clicked", Toast.LENGTH_SHORT).show()
                }
            )
        }
    ) {
        Image(
            painter = picture,
            contentDescription = "",
            modifier = Modifier.padding(16.dp).width(80.dp)
        )

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(prod.name)
            Text(prod.purchasableDate)
            Text(prod.nativeCountry)
            Text(prod.color)
        }
    }
}
