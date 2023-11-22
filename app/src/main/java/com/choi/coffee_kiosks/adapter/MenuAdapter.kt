package com.choi.coffee_kiosks.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.databinding.ItemMenuBinding
import com.choi.coffee_kiosks.model.Menu
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow

class MenuAdapter(private val menus: List<Menu>,
    private val onClick: (Menu)->Unit) :
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

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menus[position])
    }

    override fun getItemCount() = menus.size

    inner class MenuViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(menu: Menu) {
            with(binding) {
                menuImageView.setImageResource(menu.image)
                menuTextView.text = menu.name
                priceTextView.text = menu.price.toString()+" 원"

                root.setOnAvoidDuplicateClickWithFlow {
                    onClick(menu)
                }
            }

        }
    }

}