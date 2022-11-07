package com.example.shoppingapp_04.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shoppingapp_04.presentation.ui.OnBoardingFragment

class OnBoardingAdapter(
    fragmentActivity: FragmentActivity,
    private val layouts: List<Int>
) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return layouts.size
    }
    override fun createFragment(position: Int): Fragment {
        return OnBoardingFragment.newInstance(layouts[position])
    }
}