package com.ar.sihproject

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapterScan(fragment : ScanAndView) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if(position == 0)
            return ScanViaCode()
        return StartScanning()
    }
}