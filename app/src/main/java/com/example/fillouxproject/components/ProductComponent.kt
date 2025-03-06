package com.example.fillouxproject.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fillouxproject.R
import com.example.fillouxproject.datas.Product

/**
 * Composable représentant un composant de produit.
 * Affiche les détails d'un produit et gère les interactions utilisateur pour la suppression et la modification.
 *
 * @param prod Produit à afficher.
 * @param handleDelete Fonction appelée lors de la suppression du produit.
 * @param handleModify Fonction appelée lors de la modification du produit.
 * @author Filloux
 */
@Composable
fun ProductComponent(prod: Product, handleDelete: () -> Unit, handleModify: () -> Unit) {
    /**
     * Ressource d'image représentant le produit.
     * Chargée à partir des ressources de l'application.
     */
    val picture = painterResource(R.drawable.socks1)

    /**
     * Contexte local pour afficher des messages Toast.
     */
    val context = LocalContext.current

    Row(modifier = Modifier
        .padding(16.dp)
        .pointerInput(Unit) {
            detectTapGestures(
                onLongPress = {
                    handleDelete()
                },
                onTap = {
                    /**
                     * Affiche un message Toast lorsque le produit est cliqué.
                     */
                    Toast.makeText(context, "Product: ${prod.name} clicked", Toast.LENGTH_SHORT).show()
                },
                onDoubleTap = {
                    handleModify()
                }
            )
        }
    ) {
        /**
         * Image du produit.
         */
        Image(
            painter = picture,
            contentDescription = "",
            modifier = Modifier.padding(16.dp).width(80.dp)
        )

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(prod.name)
            Text(prod.purchasableDate)
            Text(prod.nativeCountry)
            Text(prod.color)
        }
    }
}
