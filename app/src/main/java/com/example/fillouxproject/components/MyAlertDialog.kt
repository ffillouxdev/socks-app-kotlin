package com.example.fillouxproject.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.example.fillouxproject.datas.ProductType

/**
 * Composable représentant une boîte de dialogue d'alerte personnalisée.
 * Affiche les informations saisies par l'utilisateur et permet de confirmer l'ajout d'un produit.
 *
 * @param shouldShowDialog État mutable indiquant si la boîte de dialogue doit être affichée.
 * @param radioButtonValue Valeur sélectionnée du bouton radio.
 * @param textFieldsValues Liste des valeurs des champs de texte.
 * @param checkBoxValue Valeur de la case à cocher indiquant si le produit est en favori.
 * @param handleAdd Fonction appelée lorsque l'utilisateur confirme l'ajout.
 * @author Filloux
 */
@Composable
fun MyAlertDialog(
    shouldShowDialog: MutableState<Boolean>,
    radioButtonValue: ProductType,
    textFieldsValues: List<String>,
    checkBoxValue: Boolean,
    handleAdd : () -> Unit
) {
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
            },
            title = { Text(text = "Dialog qui affiche les infos.") },
            text = {
                Column() {
                    /**
                     * Affiche la valeur sélectionnée du bouton radio.
                     */
                    Text(text = "valeur radio: ${radioButtonValue.label}")

                    /**
                     * Affiche les valeurs des champs de texte.
                     */
                    for (i in textFieldsValues.indices) {
                        Text(text = "textfield $i: ${textFieldsValues[i]}")
                    }

                    /**
                     * Affiche si le produit est en favori ou non.
                     */
                    Text(text = "le produit ${isFavori(checkBoxValue)}")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                        handleAdd()
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFFBB03C)),
                    ) {
                    Text(
                        text = "Confirmer",
                        color = Color.Black
                    )
                }
            }
        )
    }
}

/**
 * Fonction utilitaire pour déterminer si un produit est en favori.
 *
 * @param isFavori Valeur booléenne indiquant si le produit est en favori.
 * @return Chaîne de caractères indiquant si le produit est en favori ou non.
 */
fun isFavori(isFavori: Boolean): String {
    return if (isFavori) {
        "est en favori"
    } else {
        "n'est pas en favori"
    }
}
