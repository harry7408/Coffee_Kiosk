package com.choi.coffee_kiosks.view.place

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.adapter.KioskPlaceAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentKioskPlaceInfoBinding
import com.choi.coffee_kiosks.entity.KioskPosition
import com.choi.coffee_kiosks.network.RetrofitManager
import com.choi.coffee_kiosks.util.common.LOG_TAG
import com.choi.coffee_kiosks.util.common.composePosition
import com.choi.coffee_kiosks.util.common.places
import com.choi.coffee_kiosks.util.common.showToastMessage
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KioskPlaceInfoFragment :
    BaseFragment<FragmentKioskPlaceInfoBinding>(FragmentKioskPlaceInfoBinding::inflate),
    OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

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
        val city=LatLng(37.4675303, 126.89437)
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(city, 14.0F))
        addCoffeeMarker()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RetrofitManager.kioskPositionService.getInfo()
            .enqueue(object : Callback<com.choi.coffee_kiosks.entity.Response> {
                override fun onResponse(
                    call: Call<com.choi.coffee_kiosks.entity.Response>,
                    response: Response<com.choi.coffee_kiosks.entity.Response>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        placeAdapter.placeList = result!!.data
                        binding.bottomSheet.kioskListRecyclerView.apply {
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL, false
                            )
                            adapter = placeAdapter

                        }
                        setMarker(result.data)

                    } else {
                        requireActivity().showToastMessage("에러")
                    }
                }

                override fun onFailure(
                    call: Call<com.choi.coffee_kiosks.entity.Response>,
                    t: Throwable
                ) {
                    Log.e(LOG_TAG, t.printStackTrace().toString())
                }
            })
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