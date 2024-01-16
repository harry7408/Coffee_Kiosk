package com.choi.coffee_kiosks.view.place

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.adapter.KioskPlaceAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentKioskPlaceInfoBinding
import com.choi.coffee_kiosks.entity.KioskPosition
import com.choi.coffee_kiosks.network.RetrofitManager
import com.choi.coffee_kiosks.repository.PlaceRepositoryImpl
import com.choi.coffee_kiosks.util.common.LOG_TAG
import com.choi.coffee_kiosks.util.common.composePosition
import com.choi.coffee_kiosks.util.common.places
import com.choi.coffee_kiosks.viewmodel.PlaceViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.launch

class KioskPlaceInfoFragment :
    BaseFragment<FragmentKioskPlaceInfoBinding>(FragmentKioskPlaceInfoBinding::inflate),
    OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    // API 를 통해 불러온 데이터를 가져 오기 위한 ViewModel
    private val viewModel: PlaceViewModel by viewModels {
        PlaceViewModel.PlaceViewModelFactory(PlaceRepositoryImpl(RetrofitManager.kioskPositionService))
    }


    private var placeAdapter = KioskPlaceAdapter(emptyList(), { latLng ->
        collapseBottomSheet()
        googleMap.animateCamera(
            CameraUpdateFactory
                .newLatLngZoom(LatLng(latLng.latitude, latLng.longitude), 15.0F)
        )

    }) { phoneNum ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(phoneNum))
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKioskPlaceInfoBinding.inflate(inflater, container, false)
        mapView = binding.googleMap
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this@KioskPlaceInfoFragment)
        return binding.root
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val city = LatLng(37.4675303, 126.89437)
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(city, 14.0F))
        addCoffeeMarker()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showPlaceInfo()
    }

    // StateFlow collect 메서드를 통해 emit되는 데이터 가져 오기
    private fun showPlaceInfo() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.palceInfo.collect { placeList ->
                val places = placeList
                setMarker(places)
                placeAdapter.placeList = places

                binding.bottomSheet.kioskListRecyclerView.apply {
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL, false
                    )
                    adapter = placeAdapter
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onResume()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun setMarker(positions: List<KioskPosition>) {
        positions.forEachIndexed { _, kioskPositons ->
            googleMap.addMarker(
                MarkerOptions()
                    .position(places[kioskPositons.number]!!)
                    .title(kioskPositons.facility)
            )
        }
    }

    private fun addCoffeeMarker() {
        composePosition.forEach {
            googleMap.addMarker(
                MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.coffee_marker))
                    .position(it.value)
                    .title(it.key)
            )
        }
    }

    private fun collapseBottomSheet() {
        val bottomSheet = BottomSheetBehavior.from(binding.bottomSheet.root)
        bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}
