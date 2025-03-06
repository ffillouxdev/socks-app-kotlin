package com.example.fillouxproject.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fillouxproject.R
import com.example.fillouxproject.components.HorizontalHr
import com.example.fillouxproject.components.ProductComponent
import com.example.fillouxproject.components.navigation.Header
import com.example.fillouxproject.datas.Product
import com.example.fillouxproject.destinations.FormScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient

/**
 * Écran principal de l'application affichant la liste des produits.
 * Permet d'ajouter, modifier et supprimer des produits.
 *
 * @param navigator Gestionnaire de navigation.
 * @param resultRecipient Réception des résultats de l'écran de formulaire.
 */
@Destination(start = true)
@Composable
fun LandingScreen(
    navigator: DestinationsNavigator,
    resultRecipient: ResultRecipient<FormScreenDestination, Product>
) {
    /**
     * Liste des produits affichés à l'écran.
     */
    var listOfProducts by rememberSaveable { mutableStateOf(listOf<Product>()) }

    /**
     * Requête de recherche pour filtrer les produits.
     */
    var searchQuery by rememberSaveable { mutableStateOf("") }

    /**
     * Image affichée lorsqu'aucun produit n'est disponible.
     */
    val picture = painterResource(R.drawable.no_product_icon)

    resultRecipient.onNavResult {
        if (it is NavResult.Value) {
            listOfProducts = listOfProducts.plus(it.value)
        }
    }

    /**
     * Supprime un produit de la liste.
     * @param product Produit à supprimer.
     */
    fun handleDelete(product: Product) {
        listOfProducts = listOfProducts.filter { it != product }
    }

    /**
     * Modifie un produit en mettant à jour son nom.
     * @param product Produit à modifier.
     */
    fun handleModify(product: Product) {
        val newList = listOfProducts.map { if (it == product) it.copy(name = "Produit modifié") else it }
        listOfProducts = newList
    }

    /**
     * Liste des produits filtrés par la recherche.
     */
    val filteredProducts = listOfProducts.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    /**
     * Liste des produits favoris.
     */
    val favoriteProducts = listOfProducts.filter { it.favorite }

    Scaffold { paddingValues ->
        Column {
            /**
             * Composant d'en-tête de l'application
             */
            Header()
            /**
             * Bar de recherche
              */
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Rechercher une chaussette") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            /**
             * Texte qui renvoi Produits favoris
             */
            Text("Produits favoris", modifier = Modifier.padding(16.dp))
            if (favoriteProducts.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(favoriteProducts) { product ->
                        ProductComponent(prod = product, handleDelete = { handleDelete(product) }, handleModify = { handleModify(product) })
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    /**
                     * Icon quand il n'y a pas de produit favori
                     */
                    Image(painter = picture, contentDescription = null, modifier = Modifier.size(120.dp))
                    Text("Vous n'avez aucun produit en favori ici.", modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center)
                }
            }

             /**
              * Ligne de séparation
              */
            HorizontalHr()

            Text("Tous les produits", modifier = Modifier.padding(16.dp))
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .height(300.dp)
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (filteredProducts.isEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            /**
                             * Icon quand il n'y a pas de produit
                             */
                            Image(painter = picture, contentDescription = null, modifier = Modifier.size(120.dp))
                            Text(
                                "Vous n'avez aucun produit ici.",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        filteredProducts.forEach { product ->
                            ProductComponent(prod = product, handleDelete = { handleDelete(product) }, handleModify = { handleModify(product) })
                        }
                    }
                }
            }


            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                /**
                 * Bouton pour accéder à l'écran d'ajout de produit
                 */
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFFFBB03C)),
                    onClick = { navigator.navigate(FormScreenDestination) }
                ) {
                    /**
                     * Texte qui renvoi dans le bouton cliquer pour démarrer
                     */
                    Text(
                        text = "Cliquer pour démarrer",
                        color = Color.Black
                    )
                }
            }
        }
    }
}