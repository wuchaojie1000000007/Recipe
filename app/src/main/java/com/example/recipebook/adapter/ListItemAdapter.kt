package com.example.recipebook.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipebook.R
import com.example.recipebook.model.Flavor
import com.example.recipebook.model.ListItemUiModel
import com.example.recipebook.viewholder.CategoryViewHolder
import com.example.recipebook.viewholder.ListItemViewHolder
import com.example.recipebook.viewholder.RecipeViewHolder

class ListItemAdapter(
    private val layoutInflater: LayoutInflater,
    private val context: Context
) :
    RecyclerView.Adapter<ListItemViewHolder>() {

    companion object {
        const val CATEGORY_VIEW_TYPE = 0
        const val RECIPE_VIEW_TYPE = 1
    }

    private val itemData = mutableListOf<ListItemUiModel>()

    fun initData() {
        itemData.addAll(
            listOf(
                ListItemUiModel.Category(Flavor.Savory),
                ListItemUiModel.Category(Flavor.Sweet)
            )
        )
        notifyDataSetChanged()
        Log.d(TAG, "itemData is $itemData")
    }

    // Add a single data
    fun addData(listItemUiModel: ListItemUiModel.Recipe) {
        val position = when (listItemUiModel.flavor) {
            Flavor.Savory -> itemData.indexOfFirst { it is ListItemUiModel.Category && it.flavor == Flavor.Sweet }
            Flavor.Sweet -> itemData.size
        }
        itemData.add(position, listItemUiModel)
        notifyItemInserted(position)
        Log.d(TAG, "itemData is $itemData")

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return when (viewType) {
            CATEGORY_VIEW_TYPE -> {
                val view = layoutInflater.inflate(R.layout.item_category, parent, false)
                CategoryViewHolder(view)
            }
            RECIPE_VIEW_TYPE -> {
                val view = layoutInflater.inflate(R.layout.item_recipe, parent, false)
                RecipeViewHolder(context, view)
            }
            else -> {
                throw IllegalArgumentException("Wrong viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bindData(itemData[position])
    }

    override fun getItemCount() = itemData.size

    override fun getItemViewType(position: Int) = when (itemData[position]) {
        is ListItemUiModel.Category -> CATEGORY_VIEW_TYPE
        is ListItemUiModel.Recipe -> RECIPE_VIEW_TYPE
    }
}

private const val TAG = "ListItemAdapter"