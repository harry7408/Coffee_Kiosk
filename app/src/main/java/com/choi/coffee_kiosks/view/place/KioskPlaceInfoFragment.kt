package com.choi.coffee_kiosks.view.place

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.adapter.KioskPlaceAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentKioskPlaceInfoBinding
import com.choi.coffee_kiosks.model.KioskPosition
import com.choi.coffee_kiosks.rest.RetrofitManager
import com.choi.coffee_kiosks.util.common.API_KEY
import com.choi.coffee_kiosks.util.common.LOG_TAG
import com.choi.coffee_kiosks.util.common.showToastMessage
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KioskPlaceInfoFragment :
    BaseFragment<FragmentKioskPlaceInfoBinding>(FragmentKioskPlaceInfoBinding::inflate),
    OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

//    private val placeAdapter = KioskPlaceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_kiosk_place_info, container, false)
        mapView = view.findViewById(R.id.googleMap)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this@KioskPlaceInfoFragment)
        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val city = LatLng(37.4567667, 126.8954005)
        googleMap.addMarker(
            MarkerOptions()
                .position(city)
                .title("금천구청")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(city))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RetrofitManager.kioskPositionService.getInfo()
            .enqueue(object : Callback<com.choi.coffee_kiosks.model.Response> {
                override fun onResponse(
                    call: Call<com.choi.coffee_kiosks.model.Response>,
                    response: Response<com.choi.coffee_kiosks.model.Response>
                ) {
                    if (response.isSuccessful) {

                    } else {
                        requireContext().showToastMessage("에러")
                    }
                }

                override fun onFailure(
                    call: Call<com.choi.coffee_kiosks.model.Response>,
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

}