package com.choi.coffee_kiosks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choi.coffee_kiosks.databinding.ItemSelectedMenuBinding
import com.choi.coffee_kiosks.model.SelectedMenu

class SelectedMenuAdapter(private val selectedMenu: List<SelectedMenu>
) : RecyclerView.Adapter<SelectedMenuAdapter.SelectedViewHolder>() {

    inner class SelectedViewHolder(private val binding: ItemSelectedMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(menu:SelectedMenu) {
            with(binding) {
                menuImageView.setImageResource(menu.image)
                countTextView.text=menu.count.toString()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedMenuAdapter.SelectedViewHolder {
        return SelectedViewHolder(
            ItemSelectedMenuBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
        holder.bind(selectedMenu[position])
    }

    override fun getItemCount(): Int = selectedMenu.size


}

