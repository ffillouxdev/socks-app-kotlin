package com.example.fillouxproject.datas

import androidx.compose.runtime.MutableState
import java.io.Serializable

/**
 * Classe de données représentant un produit.
 * Contient les informations de base sur un produit, y compris son nom, sa date d'achat, sa couleur,
 * son pays d'origine, s'il est favori, et son type de produit.
 *
 * @property name Nom du produit.
 * @property purchasableDate Date à laquelle le produit peut être acheté.
 * @property color Couleur du produit.
 * @property nativeCountry Pays d'origine du produit.
 * @property favorite Indique si le produit est marqué comme favori.
 * @property productType Type de produit, représenté par un état mutable.
 * @author Filloux
 */
data class Product(
    val name: String,
    val purchasableDate: String,
    val color: String,
    val nativeCountry: String,
    val favorite: Boolean,
    val productType: MutableState<ProductType>
) : Serializable
