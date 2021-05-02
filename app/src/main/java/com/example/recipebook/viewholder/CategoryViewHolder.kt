package com.example.recipebook.viewholder

import android.view.View
import android.widget.TextView
import com.example.recipebook.R
import com.example.recipebook.model.Flavor
import com.example.recipebook.model.ListItemUiModel

class CategoryViewHolder(containerView: View) : ListItemViewHolder(containerView) {

    private val flavorText: TextView by lazy { containerView.findViewById(R.id.flavor_text) }

    override fun bindData(listItemUiModel: ListItemUiModel) {
        require(listItemUiModel is ListItemUiModel.Category)
        flavorText.text = when (listItemUiModel.flavor) {
            Flavor.Savory -> "Savory"
            Flavor.Sweet -> "Sweet"
        }
    }


}