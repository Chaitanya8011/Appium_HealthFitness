package com.example.healthfitnesstrackerapp

import Fragments.OnboardingSlide1Fragment
import Fragments.OnboardingSlide2Fragment
import Fragments.OnboardingSlide3Fragmen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3 // Number of slides
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingSlide1Fragment()
            1 -> OnboardingSlide2Fragment()
            2 -> OnboardingSlide3Fragmen()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}