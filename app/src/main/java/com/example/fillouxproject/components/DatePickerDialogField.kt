package com.example.fillouxproject.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.launch

/**
 * Composable représentant un champ de sélection de date avec un dialogue.
 * Permet à l'utilisateur de sélectionner une date via un dialogue de sélection de date.
 *
 * @param label Libellé du champ de texte.
 * @param initialValue Valeur initiale du champ de texte.
 * @param required Indique si le champ est obligatoire.
 * @param onValueChange Fonction appelée lorsque la valeur du champ change.
 * @param snackbarHostState État du Snackbar pour afficher des messages.
 * @param scope CoroutineScope pour lancer des coroutines.
 * @author Filloux
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogField(
    label: String,
    initialValue: String = "",
    required: Boolean = false,
    onValueChange: (String) -> Unit,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
) {
    /**
     * État indiquant si le dialogue de sélection de date est ouvert.
     */
    val openDialog = remember { mutableStateOf(false) }

    /**
     * Valeur actuelle du champ de texte, formatée en date.
     */
    var value by remember { mutableStateOf(initialValue) }

    /**
     * Formate une date en chaîne de caractères.
     *
     * @param millis Temps en millisecondes à formater.
     * @return Date formatée en chaîne de caractères (dd/MM/yyyy).
     */
    fun formatDate(millis: Long?): String {
        return millis?.let {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            dateFormat.format(calendar.time)
        } ?: ""
    }

    TextField(
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        enabled = false,
        label = { Text(text = if (required) "$label *" else label) },
        modifier = Modifier.clickable { openDialog.value = true }
    )

    if (openDialog.value) {
        /**
         * État du sélecteur de date.
         */
        val datePickerState = rememberDatePickerState()

        /**
         * Indique si le bouton de confirmation est activé.
         */
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }

        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Date sélectionnée : ${formatDate(datePickerState.selectedDateMillis)}"
                            )
                        }
                        datePickerState.selectedDateMillis?.let {
                            value = formatDate(it)
                            onValueChange(value)
                        }
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(
                state = datePickerState,
                modifier = Modifier.verticalScroll(rememberScrollState())
            )
        }
    }
}
