package com.example.recipebook.model

sealed class ListItemUiModel {
    data class Category(val flavor: Flavor) : ListItemUiModel()
    data class Recipe(val title: String, val description: String, val flavor: Flavor) :
        ListItemUiModel()
}
