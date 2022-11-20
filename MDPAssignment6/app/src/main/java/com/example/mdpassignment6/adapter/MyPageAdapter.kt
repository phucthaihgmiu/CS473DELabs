package com.example.mdpassignment6.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mdpassignment6.AboutFragment
import com.example.mdpassignment6.ContactFragment
import com.example.mdpassignment6.HomeFragment
import com.example.mdpassignment6.WorkFragment

class MyPageAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 4 // We have 4 fragments

    // Provide a new Fragment associated with the specified position.
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> AboutFragment()
            2 -> WorkFragment()
            3 -> ContactFragment()
            else -> Fragment()

        }
    }

}