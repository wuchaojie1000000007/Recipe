package com.example.recipebook

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipebook.adapter.ListItemAdapter
import com.example.recipebook.databinding.ActivityMainBinding
import com.example.recipebook.model.Flavor
import com.example.recipebook.model.ListItemUiModel

class MainActivity : AppCompatActivity() {

    private val adapter: ListItemAdapter by lazy { ListItemAdapter(layoutInflater, this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        adapter.initData()
        binding.addSavoryButton.setOnClickListener {
            addData(Flavor.Savory)
        }
        binding.addSweetButton.setOnClickListener {
            addData(Flavor.Sweet)
        }
    }

    private fun checkDataFillUp() = binding.titleText.text.toString().isNotEmpty()
            && binding.descriptionText.text.toString().isNotEmpty()

    private fun addData(flavor: Flavor) {
        if (!checkDataFillUp()) {
            Toast.makeText(this, "Please fill up title and description", Toast.LENGTH_LONG)
                .show()
        } else {
            val title = binding.titleText.text.toString()
            val description = binding.descriptionText.text.toString()
            val data = ListItemUiModel.Recipe(title, description, flavor)
            adapter.addData(
                data.also { Log.d(TAG, "add a recipe: $it") }
            )
        }
        setUpForTheNextRecipe()
    }

    private fun setUpForTheNextRecipe(){
        this.hideKeyboard()
        clearEditText()
        clearFocus()
    }


    private fun clearEditText() {
        binding.titleText.text?.clear()
        binding.descriptionText.text?.clear()
    }

    private fun clearFocus(){
        binding.titleText.clearFocus()
        binding.descriptionText.clearFocus()
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

private const val TAG = "MainActivity"