package com.ar.sihproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Favourites : Fragment() {

    lateinit var recyclerView : RecyclerView
    lateinit var adapter: FavouritesAdapter
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    var newArray = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //connect to firebase, if no likes found. Display connect to view. It'll be stored as array

        var v : View =  inflater.inflate(R.layout.fragment_favourites, container, false)

        recyclerView = v.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        auth = Firebase.auth
        val userUID = auth.currentUser?.uid

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if(document.data["User UID"] == userUID){
                        newArray.add(document.data["Favourites"].toString())
                    }
                }
            }

        adapter = FavouritesAdapter(newArray)
        recyclerView.adapter = adapter

        return v

    }

}