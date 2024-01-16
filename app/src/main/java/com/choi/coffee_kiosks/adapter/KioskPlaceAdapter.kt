package com.choi.coffee_kiosks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choi.coffee_kiosks.databinding.ItemKioskPlaceBinding
import com.choi.coffee_kiosks.entity.KioskPosition
import com.choi.coffee_kiosks.util.common.places
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.telephoneNum
import com.google.android.gms.maps.model.LatLng

// 키오스크 위치 확인 기능의 BottomSheet 의 항목을 위한 Adapter
class KioskPlaceAdapter(
    var placeList: List<KioskPosition>,
    private val onClick: (LatLng) -> Unit,
    private val callImageClick: (String) -> Unit,
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
                installedPlaceTextView.text = place.installedPlace
                departmentTextView.text = place.department

                root.setOnAvoidDuplicateClickWithFlow {
                    onClick(places[place.number]!!)
                }

                callImageView.setOnAvoidDuplicateClickWithFlow {
                    callImageClick("tel:${telephoneNum[place.number]}")
                }
            }
        }
    }
}