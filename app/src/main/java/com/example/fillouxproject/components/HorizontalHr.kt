package com.example.fillouxproject.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Composable représentant une ligne de séparation horizontale.
 * Affiche une ligne grise centrée horizontalement pour séparer visuellement le contenu.
 *
 * @author Filloux
 */
@Composable
fun HorizontalHr() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        /**
         * Séparateur horizontal.
         * Utilisé pour séparer visuellement différentes sections de l'interface utilisateur.
         */
        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(1.dp)
        )
    }
}
