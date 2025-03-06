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
    val openDialog = remember { mutableStateOf(false) }
    var value by remember { mutableStateOf(initialValue) }

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
        val datePickerState = rememberDatePickerState()
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
