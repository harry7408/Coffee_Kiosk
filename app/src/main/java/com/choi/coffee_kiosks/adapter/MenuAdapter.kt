package com.choi.coffee_kiosks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.databinding.ItemMenuBinding
import com.choi.coffee_kiosks.model.Menu

class MenuAdapter(private val menus: List<Menu>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.MenuViewHolder {
        return MenuViewHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuAdapter.MenuViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = menus.size

    inner class MenuViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menu) {
            with(binding) {
                menuImageView.setImageResource(menu.image)
                menuTextView.text = menu.name
                priceTextView.text = menu.price
            }

        }
    }

}