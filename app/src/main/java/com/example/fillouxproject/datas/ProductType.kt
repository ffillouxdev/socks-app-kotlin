package com.example.fillouxproject.datas

/**
 * Énumération représentant les différents types de produits.
 * Chaque type de produit a un libellé associé.
 *
 * @property Consumable Type de produit consommable.
 * @property Durable Type de produit durable.
 * @property Other Type de produit autre.
 * @author Filloux
 */
enum class ProductType {
    /**
     * Produit consommable.
     */
    Consumable,

    /**
     * Produit durable.
     */
    Durable,

    /**
     * Autre type de produit.
     */
    Other;

    /**
     * Libellé associé au type de produit.
     */
    val label: String
        get() = when(this) {
            Consumable -> "Consumable"
            Durable -> "Durable"
            Other -> "Autre"
        }
}
