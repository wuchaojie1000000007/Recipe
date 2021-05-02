package com.example.recipebook.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recipebook.model.ListItemUiModel

abstract class ListItemViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
    abstract fun bindData(listItemUiModel: ListItemUiModel)
}