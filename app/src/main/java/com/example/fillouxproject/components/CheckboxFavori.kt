package com.example.fillouxproject.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Composable représentant une case à cocher pour ajouter un produit aux favoris.
 * Affiche une case à cocher avec un texte descriptif.
 *
 * @param checkBoxValue État mutable indiquant si la case est cochée ou non.
 * @author Filloux
 */
@Composable
fun CheckboxFavori(checkBoxValue: MutableState<Boolean>) {
    Row(
        Modifier
            .selectableGroup()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        /**
         * Case à cocher permettant d'ajouter ou de retirer un produit des favoris.
         * La couleur change en fonction de l'état coché ou non.
         */
        Checkbox(
            checked = checkBoxValue.value,
            onCheckedChange = { checkBoxValue.value = it },
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFFFBB03C),
                uncheckedColor = Color.Gray,
                checkmarkColor = Color.Black
            )
        )
        /**
         * Texte descriptif associé à la case à cocher.
         * Indique l'action d'ajouter aux favoris.
         */
        Text("Ajouter aux favoris")
    }
}