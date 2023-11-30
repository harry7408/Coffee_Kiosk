package com.choi.coffee_kiosks.view.mission

import android.os.Bundle
import android.view.View
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.adapter.ViewPagerAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentMissionBinding
import com.google.android.material.tabs.TabLayoutMediator


class MissionFragment : BaseFragment<FragmentMissionBinding>(FragmentMissionBinding::inflate) {

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabList = listOf<String>(
            getString(R.string.easy_text), getString(R.string.normal_text),
            getString(R.string.hard_text)
        )

        viewPagerAdapter = ViewPagerAdapter(this)

        viewPagerAdapter.addFragment(EasyFragment())
        viewPagerAdapter.addFragment(NormalFragment())
        viewPagerAdapter.addFragment(HardFragment())

        binding.viewpager2.adapter = viewPagerAdapter

        TabLayoutMediator(binding.missionDifficultyTabLayout,binding.viewpager2) { tab, position ->
            tab.text=tabList[position]
        }.attach()

    }
}