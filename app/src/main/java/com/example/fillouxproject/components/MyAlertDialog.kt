package com.example.fillouxproject.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.example.fillouxproject.datas.ProductType

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
                    Text(text = "valeur radio: ${radioButtonValue.label}")

                    for (i in textFieldsValues.indices) {
                        Text(text = "textfield $i: ${textFieldsValues[i]}")
                    }

                    // Show the result of isFavori function
                    Text(text = "le produit ${isFavori(checkBoxValue)}")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                        handleAdd()
                    }
                ) {
                    Text(
                        text = "Confirm",
                        color = Color.White
                    )
                }
            }
        )
    }
}

fun isFavori(isFavori: Boolean): String {
    return if (isFavori) {
        "est en favori"
    } else {
        "n'est pas en favori"
    }
}
