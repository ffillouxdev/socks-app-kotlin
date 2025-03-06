package com.example.fillouxproject.datas

enum class ProductType {
    Consumable,
    Durable,
    Other;

    val label: String
        get() = when(this) {
            Consumable -> "Consumable"
            Durable -> "Durable"
            Other -> "Outre"
        }
}