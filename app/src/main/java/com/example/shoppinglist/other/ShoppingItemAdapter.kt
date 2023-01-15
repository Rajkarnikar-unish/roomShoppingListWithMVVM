package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.databinding.ShoppingItemBinding
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel,
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
//
//        return ShoppingViewHolder(ShoppingItemBinding.inflate(
//            view
//        ))

        return ShoppingViewHolder(ShoppingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        ))
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        holder.binding.apply {
            tvName.text = currentShoppingItem.name
            tvAmount.text = "${currentShoppingItem.amount}"

            ivDelete.setOnClickListener {
                viewModel.delete(currentShoppingItem)
            }

            ivPlus.setOnClickListener {
                currentShoppingItem.amount++
                viewModel.upsert(currentShoppingItem)
            }

            ivMinus.setOnClickListener {
                if(currentShoppingItem.amount > 0) {
                    currentShoppingItem.amount--
                    viewModel.upsert(currentShoppingItem)
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ShoppingViewHolder(val binding: ShoppingItemBinding) : RecyclerView.ViewHolder(binding.root)

}