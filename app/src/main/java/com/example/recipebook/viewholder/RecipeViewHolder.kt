package com.example.recipebook.viewholder

import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.recipebook.R
import com.example.recipebook.model.Flavor
import com.example.recipebook.model.ListItemUiModel

class RecipeViewHolder(
    private val context: Context,
    containerView: View
) : ListItemViewHolder(containerView) {

    private val recipeTitle: TextView by lazy { containerView.findViewById(R.id.recipe_title) }
    private val recipeDescription: TextView by lazy { containerView.findViewById(R.id.recipe_description) }

    override fun bindData(listItemUiModel: ListItemUiModel) {
        require(listItemUiModel is ListItemUiModel.Recipe)
        recipeTitle.text =
            context.resources.getString(
                R.string.recipe_title_text,
                listItemUiModel.title
            )
        recipeDescription.text = context.resources.getString(
            R.string.recipe_description_text,
            listItemUiModel.description
        )
    }
}