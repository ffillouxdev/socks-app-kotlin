package com.example.fillouxproject.components.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fillouxproject.R

/**
 * Composable représentant l'en-tête de l'application.
 * Affiche le logo de l'application et le nom "QuickShop".
 *
 * @author Filloux
 */
@Composable
fun Header() {
    /**
     * Ressource d'image représentant le logo de l'application.
     * Chargée à partir des ressources de l'application.
     */
    val picture = painterResource(R.drawable.logo_quickshop)

    Row(
        modifier = Modifier
            .padding(top = 40.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        /**
         * Image du logo de l'application.
         * Utilise la ressource d'image définie par [picture].
         */
        Image(painter = picture, contentDescription = null, modifier = Modifier.size(48.dp))

        /**
         * Texte affichant le nom de l'application "QuickShop".
         * Positionné à côté du logo.
         */
        Text("QuickShop", color = Color.Black, fontWeight = FontWeight.Bold )
    }
}