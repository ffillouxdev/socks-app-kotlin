package com.example.fillouxproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fillouxproject.R
import com.example.fillouxproject.components.*
import com.example.fillouxproject.components.navigation.Header
import com.example.fillouxproject.datas.Product
import com.example.fillouxproject.datas.ProductType
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun FormScreen(
    navigator: DestinationsNavigator,
    resultNavigator: ResultBackNavigator<Product>
) {
    val picture = painterResource(R.drawable.socks1)
    val radioButtonValue = remember { mutableStateOf(ProductType.Other) }
    val checkBoxValue = remember { mutableStateOf(false) }
    val nomProduit = remember { mutableStateOf("") }
    val dateAchat = remember { mutableStateOf("") }
    val couleur = remember { mutableStateOf("") }
    val paysOrigine = remember { mutableStateOf("") }
    val shouldShowDialog = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    fun handleNavigation() {
        val product = Product(
            name = nomProduit.value,
            purchasableDate = dateAchat.value,
            color = couleur.value,
            nativeCountry = paysOrigine.value,
            favorite = checkBoxValue.value,
            productType = radioButtonValue
        )
        resultNavigator.navigateBack(product)
    }

    fun onValidate() {
        val isVerified = listOf(nomProduit.value, dateAchat.value).all { it.isNotBlank() }
        if (isVerified) {
            shouldShowDialog.value = true
        } else {
            scope.launch {
                snackbarHostState.showSnackbar("Veuillez remplir tous les champs obligatoires (*).")
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column {
            Header()
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Formulaire")
                Image(
                    painter = picture,
                    contentDescription = null,
                    modifier = Modifier.padding(24.dp)
                )
                RadioButtons(radioButtonValue = radioButtonValue)
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    EditableTextField(
                        label = "Nom du produit",
                        initialValue = nomProduit.value,
                        required = true,
                        onValueChange = { nomProduit.value = it }
                    )
                    DatePickerDialogField(
                        label = "Date d'achat",
                        initialValue = dateAchat.value,
                        onValueChange = { dateAchat.value = it },
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        required = true
                    )
                    EditableTextField(
                        label = "Couleur",
                        initialValue = couleur.value,
                        required = false,
                        onValueChange = { couleur.value = it }
                    )
                    EditableTextField(
                        label = "Pays d'origine",
                        initialValue = paysOrigine.value,
                        required = false,
                        onValueChange = { paysOrigine.value = it }
                    )
                }
                CheckboxFavori(checkBoxValue = checkBoxValue)
                Button(
                    onClick = { onValidate() },
                    modifier = Modifier.wrapContentSize(),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFBB03C)),
                    ) {
                    Text(
                        text = "Valider",
                        color = Color.Black
                    )
                }

                if (shouldShowDialog.value) {
                    MyAlertDialog(
                        shouldShowDialog = shouldShowDialog,
                        radioButtonValue = radioButtonValue.value,
                        textFieldsValues = listOf(
                            nomProduit.value,
                            dateAchat.value,
                            couleur.value,
                            paysOrigine.value
                        ),
                        checkBoxValue = checkBoxValue.value,
                        handleAdd = { handleNavigation() }
                    )
                }
            }
        }
    }
}
