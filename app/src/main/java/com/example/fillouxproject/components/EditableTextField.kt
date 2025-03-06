package com.example.fillouxproject.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

/**
 * Composable représentant un champ de texte éditable.
 * Permet à l'utilisateur de saisir du texte avec un libellé et une indication d'obligation.
 *
 * @param label Libellé du champ de texte.
 * @param initialValue Valeur initiale du champ de texte.
 * @param required Indique si le champ est obligatoire.
 * @param onValueChange Fonction appelée lorsque la valeur du champ change.
 * @author Filloux
 */
@Composable
fun EditableTextField(
    label: String,
    initialValue: String = "",
    required: Boolean = false,
    onValueChange: (String) -> Unit
) {
    /**
     * Valeur actuelle du champ de texte, mise à jour lors de la saisie utilisateur.
     */
    var value by remember { mutableStateOf(initialValue) }
    TextField(
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        label = {
            /**
             * Libellé du champ de texte.
             * Ajoute un astérisque si le champ est obligatoire.
             */
            Text(text = if (required) "$label *" else label)
        }
    )
}
