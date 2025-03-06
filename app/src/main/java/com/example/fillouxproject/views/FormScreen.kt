package com.example.fillouxproject.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fillouxproject.R
import com.example.fillouxproject.components.CheckboxFavori
import com.example.fillouxproject.components.DatePickerDialogField
import com.example.fillouxproject.components.EditableTextField
import com.example.fillouxproject.components.RadioButtons
import com.example.fillouxproject.components.SubmitButton
import com.example.fillouxproject.datas.Product
import com.example.fillouxproject.datas.ProductType
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator

@Destination
@Composable
fun FormScreen(navigator: DestinationsNavigator, resultNavigator: ResultBackNavigator<Product>) {
    val picture = painterResource(R.drawable.socks1)
    val radioButtonValue = remember { mutableStateOf(ProductType.Other) }
    val checkBoxValue = remember { mutableStateOf(false) }
    val nomProduit = remember { mutableStateOf("") }
    val dateAchat = remember { mutableStateOf("") }
    val couleur = remember { mutableStateOf("") }
    val paysOrigine = remember { mutableStateOf("") }

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

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Formulaire")
            Image(painter=picture, contentDescription = null, modifier = Modifier.padding(24.dp))
            RadioButtons(
                radioButtonValue = radioButtonValue
            )
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp))
            {
                EditableTextField("Nom du produit*", onValueChange = { nomProduit.value = it })

                DatePickerDialogField(
                    label = "Date d'achat",
                    initialValue = dateAchat.value,
                    onValueChange = { dateAchat.value = it }
                )

                EditableTextField("Couleur", onValueChange = { couleur.value = it })
                EditableTextField("Pays d'origine", onValueChange = { paysOrigine.value = it })
            }
            CheckboxFavori(
                checkBoxValue = checkBoxValue
            )
            SubmitButton(
                radioButtonValue = radioButtonValue,
                textFieldsValues = listOf(nomProduit.value, dateAchat.value, couleur.value, paysOrigine.value),
                checkBoxValue = checkBoxValue.value,
                handleNav = { handleNavigation() }
            )
        }
    }
}
