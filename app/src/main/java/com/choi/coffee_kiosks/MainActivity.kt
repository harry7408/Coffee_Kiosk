package com.choi.coffee_kiosks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.choi.coffee_kiosks.databinding.ActivityMainBinding
import com.choi.coffee_kiosks.model.pref.FreeOptionPreference
import com.choi.coffee_kiosks.model.pref.NonFreeOptionPreference
import com.choi.coffee_kiosks.util.common.AppUtil
import com.choi.coffee_kiosks.util.common.LOG_TAG
import com.choi.coffee_kiosks.view.home.HomeFragment
import com.choi.coffee_kiosks.viewModels.MainViewModel
import com.choi.coffee_kiosks.viewModels.SelectedMenuViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val selectedViewModel: SelectedMenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Splash 이미지 넣기
        installSplashScreen()

        val uuid = AppUtil.getUUID(this)
        Log.d(LOG_TAG, "Main ${uuid.toString()}")
        mainViewModel.userUUID = uuid

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        if (savedInstanceState == null) {
            // 홈 화면 등록 (Fragment로)
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainer.id, HomeFragment.newInstance())
                .commit()
        }
    }

    override fun onPause() {
        super.onPause()
        val freeOptionPreference=FreeOptionPreference.getInstance(this)
        val nonFreeOptionPreference=NonFreeOptionPreference.getInstance(this)
        freeOptionPreference.clearData()
        nonFreeOptionPreference.clearData()
    }
}