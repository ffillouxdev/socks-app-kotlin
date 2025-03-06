package com.example.fillouxproject.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fillouxproject.components.ProductComponent
import com.example.fillouxproject.components.navigation.Header
import com.example.fillouxproject.datas.Product
import com.example.fillouxproject.views.destinations.FormScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient

@Destination(start = true)
@Composable
fun LandingScreen(navigator: DestinationsNavigator, resultRecipient: ResultRecipient<FormScreenDestination, Product>) {
    var listOfProducts by rememberSaveable { mutableStateOf(listOf<Product>()) }

    resultRecipient.onNavResult {
        if (it is NavResult.Value){
            listOfProducts = listOfProducts.plus(it.value)
        }
    }

    fun handleDele(product: Product) {
        listOfProducts = listOfProducts.filter { it != product }
    }

    Scaffold {
        Column {
            Header()

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (listOfProducts.isEmpty()) {
                    Text("La liste est vide")
                } else {
                    LazyColumn {
                        items(listOfProducts) { product ->
                            ProductComponent(prod = product, handleDelete = { handleDele(product) })
                        }
                    }
                }
                Button(onClick = { navigator.navigate(FormScreenDestination) }) {
                    Text("Cliquer pour démarrer")
                }
            }
        }
    }
}