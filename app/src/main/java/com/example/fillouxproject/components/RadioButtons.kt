package com.example.fillouxproject.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fillouxproject.datas.ProductType

/**
 * Composable représentant un groupe de boutons radio pour sélectionner le type de produit.
 * Affiche les options de type de produit avec des boutons radio.
 *
 * @param radioButtonValue État mutable indiquant le type de produit sélectionné.
 * @author Filloux
 */
@Composable
fun RadioButtons(radioButtonValue: MutableState<ProductType>) {
    Row(Modifier
        .selectableGroup()
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        /**
         * Option "Autre" pour le type de produit.
         */
        Text(
            "Autre",
            modifier = Modifier.padding(12.dp),
            fontSize = 12.sp,
            color = Color.Black
        )
        RadioButton(
            selected = radioButtonValue.value == ProductType.Other,
            onClick = { radioButtonValue.value = ProductType.Other },
            colors = RadioButtonDefaults.colors(Color(0xFFFBB03C))
            )

        /**
         * Option "Consommable" pour le type de produit.
         */
        Text(
            ProductType.Consumable.label,
            modifier = Modifier.padding(12.dp),
            fontSize = 12.sp,
            color = Color.Black
        )
        RadioButton(
            selected = radioButtonValue.value == ProductType.Consumable,
            onClick = { radioButtonValue.value = ProductType.Consumable },
            colors = RadioButtonDefaults.colors(Color(0xFFFBB03C))
        )
        /**
         * Option "Durable" pour le type de produit.
         */
        Text(
            "Durable",
            modifier = Modifier.padding(12.dp),
            fontSize = 12.sp,
            color = Color.Black
        )
        RadioButton(
            selected = radioButtonValue.value == ProductType.Durable,
            onClick = { radioButtonValue.value = ProductType.Durable },
            colors = RadioButtonDefaults.colors(Color(0xFFFBB03C))
        )
    }
}
