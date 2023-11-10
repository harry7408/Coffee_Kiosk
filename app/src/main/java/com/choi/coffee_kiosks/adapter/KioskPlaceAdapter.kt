package com.choi.coffee_kiosks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choi.coffee_kiosks.databinding.ItemKioskPlaceBinding
import com.choi.coffee_kiosks.model.KioskPosition
import com.google.android.gms.maps.model.LatLng

class KioskPlaceAdapter(
    private val placeList: List<KioskPosition>,
    private val onClick: (LatLng) -> Unit,
    private val callImageClick: (View) -> Unit,
) :
    RecyclerView.Adapter<KioskPlaceAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(
            ItemKioskPlaceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = placeList.size

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(placeList[position])
    }

    inner class PlaceViewHolder(private val binding: ItemKioskPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(place: KioskPosition) {
            with(binding) {
                facilityTextView.text = place.facility
                addressTextView.text = place.address
                installedPlaceTextView.text = place.installedPlace
                departmentTextView.text = place.department
            }


        }
    }
}