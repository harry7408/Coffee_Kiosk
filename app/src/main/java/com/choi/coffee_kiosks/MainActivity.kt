package com.choi.coffee_kiosks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.choi.coffee_kiosks.databinding.ActivityMainBinding
import com.choi.coffee_kiosks.view.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
       if (savedInstanceState==null) {
           // 홈 화면 등록 (Fragment로)
           supportFragmentManager.beginTransaction()
               .add(binding.fragmentContainer.id, HomeFragment.newInstance())
               .commit()
       }
    }
}