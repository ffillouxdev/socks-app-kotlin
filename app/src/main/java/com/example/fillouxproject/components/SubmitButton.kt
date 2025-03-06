package com.example.fillouxproject.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fillouxproject.datas.ProductType
import kotlinx.coroutines.launch

@Composable
fun SubmitButton(
    radioButtonValue: MutableState<ProductType>,
    textFieldsValues: List<String>,
    checkBoxValue: Boolean,
    handleNav : () -> Unit
) {
    val shouldShowDialog = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (shouldShowDialog.value) {
                MyAlertDialog(
                    shouldShowDialog = shouldShowDialog,
                    radioButtonValue = radioButtonValue.value,
                    textFieldsValues = textFieldsValues,
                    checkBoxValue = checkBoxValue,
                    handleAdd = handleNav
                )
            }

            Button(
                onClick = {
                    val isVerified = textFieldsValues.all { it.isNotBlank() }
                    if (isVerified) {
                        shouldShowDialog.value = true
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("Veuillez remplir tous les champs obligatoires (*).")
                        }
                    }
                },
                modifier = Modifier.wrapContentSize()
            ) {
                Text(text = "Valider")
            }
        }
    }
}
