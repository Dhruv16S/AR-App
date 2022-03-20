package com.ar.sihproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ScanAndView : Fragment() {

    lateinit var viewPager : ViewPager2
    lateinit var tabLayout : TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v : View = inflater.inflate(R.layout.fragment_scan_and_view, container, false)

        viewPager = v.findViewById(R.id.viewPager)
        tabLayout = v.findViewById(R.id.tabs)

        val newAdapter = ViewPagerAdapterScan(this)
        viewPager.adapter = newAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if(position == 0)
                tab.text = "Code/QR"
            else
                tab.text = "Scan Landmark"
        }.attach()
        return v

    }

}