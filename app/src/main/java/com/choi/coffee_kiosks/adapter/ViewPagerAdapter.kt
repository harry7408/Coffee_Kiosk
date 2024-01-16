package com.choi.coffee_kiosks.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

// 미션의 난이도 부분에 적용된 ViewPager 의 Adapter
class ViewPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList= mutableListOf<Fragment>()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    /**
     * Add fragment
     * ViewPager 에 Fragment 추가
     * @param fragment
     */
    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
        notifyItemInserted(fragmentList.size-1)
    }
}