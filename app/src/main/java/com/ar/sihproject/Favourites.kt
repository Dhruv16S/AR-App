package com.ar.sihproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class Favourites : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //connect to firebase, if no likes found. Display connect to view. It'll be stored as array

        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

}