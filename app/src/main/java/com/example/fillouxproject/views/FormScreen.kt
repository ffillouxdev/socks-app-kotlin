package com.example.fillouxproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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

/**
 * Écran du formulaire permettant à l'utilisateur de saisir les informations d'un produit.
 *
 * Cet écran permet de renseigner le nom, la date d'achat, la couleur, le pays d'origine,
 * le type de produit ainsi que la possibilité de le marquer comme favori.
 * Une validation est effectuée pour s'assurer que tous les champs obligatoires sont remplis.
 *
 * @param navigator Permet la navigation entre les écrans.
 * @param resultNavigator Gère le retour des données après validation du formulaire.
 */
@Destination
@Composable
fun FormScreen(
    navigator: DestinationsNavigator,
    resultNavigator: ResultBackNavigator<Product>
) {
    /**
     * Image représentant le produit.
     */
    val picture = painterResource(R.drawable.socks1)

    /**
     * Type de produit sélectionné via des boutons radio.
     */
    val radioButtonValue = remember { mutableStateOf(ProductType.Other) }

    /**
     * Indique si le produit est marqué comme favori.
     */
    val checkBoxValue = remember { mutableStateOf(false) }

    /**
     * Nom du produit saisi par l'utilisateur.
     */
    val nomProduit = remember { mutableStateOf("") }

    /**
     * Date d'achat du produit.
     */
    val dateAchat = remember { mutableStateOf("") }

    /**
     * Couleur du produit.
     */
    val couleur = remember { mutableStateOf("") }

    /**
     * Pays d'origine du produit.
     */
    val paysOrigine = remember { mutableStateOf("") }

    /**
     * Indique si la boîte de dialogue de confirmation doit être affichée.
     */
    val shouldShowDialog = remember { mutableStateOf(false) }

    /**
     * Gère l'affichage des messages Snackbar.
     */
    val snackbarHostState = remember { SnackbarHostState() }

    /**
     * Portée de coroutine pour exécuter des tâches asynchrones.
     */
    val scope = rememberCoroutineScope()

    /**
     * Gère la navigation après validation du formulaire.
     * Crée un objet `Product` et renvoie ce résultat au navigateur.
     */
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

    /**
     * Vérifie si tous les champs obligatoires sont remplis.
     * Si c'est le cas, affiche une boîte de dialogue de confirmation.
     * Sinon, affiche un message d'erreur dans une `Snackbar`.
     */
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
            /**
             * Header de l'application
             */
            Header()
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                /**
                 * Texte qui renvoi formulaire
                 */
                Text("Formulaire")

                /**
                 * Image de chaussette
                 */
                Image(
                    painter = picture,
                    contentDescription = null,
                    modifier = Modifier.padding(24.dp)
                )

                /**
                 * Composant affichant les radios buttons Other, durable etc...
                 */
                RadioButtons(radioButtonValue = radioButtonValue)
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    /**
                     * Composant champ de saisie
                     */
                    EditableTextField(
                        label = "Nom du produit",
                        initialValue = nomProduit.value,
                        required = true,
                        onValueChange = { nomProduit.value = it }
                    )

                    /**
                     * Composant champ de saisie + popup de date
                     */
                    DatePickerDialogField(
                        label = "Date d'achat",
                        initialValue = dateAchat.value,
                        onValueChange = { dateAchat.value = it },
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                        required = true
                    )
                    /**
                     * Composant champ de saisie
                     */
                    EditableTextField(
                        label = "Couleur",
                        initialValue = couleur.value,
                        required = false,
                        onValueChange = { couleur.value = it }
                    )
                    /**
                     * Composant champ de saisie
                     */
                    EditableTextField(
                        label = "Pays d'origine",
                        initialValue = paysOrigine.value,
                        required = false,
                        onValueChange = { paysOrigine.value = it }
                    )
                }

                /**
                 * Composant de checkbox pour les favoris
                 */
                CheckboxFavori(checkBoxValue = checkBoxValue)

                /**
                 * Bouton de submit
                 */
                Button(
                    onClick = { onValidate() },
                    modifier = Modifier.wrapContentSize(),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFBB03C)),
                ) {
                    /**
                     * Texte qui renvoie valider dans le bouton
                     */
                    Text(
                        text = "Valider",
                        color = Color.Black
                    )
                }

                /**
                 * Bouton de recap des infos saisies
                 */
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