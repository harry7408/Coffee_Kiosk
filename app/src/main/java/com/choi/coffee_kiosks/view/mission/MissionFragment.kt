package com.choi.coffee_kiosks.view.mission

import android.os.Bundle
import android.util.Log
import android.view.View
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentMissionBinding
import com.choi.coffee_kiosks.rest.FirebaseRetrofitManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MissionFragment:BaseFragment<FragmentMissionBinding>(FragmentMissionBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val service=FirebaseRetrofitManager.missionService

        CoroutineScope(Dispatchers.IO).launch {
            val response=service.getMission()

            if (response.isSuccessful) {
                Log.d("TAG",response.body().toString())
            } else {
                Log.d("TAG","aaaa")
            }
        }

    }


}