package com.ar.sihproject

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ScanViaCode : Fragment() {

    lateinit var monumentName : TextInputEditText
    lateinit var landmarkCode : TextInputEditText
    lateinit var view : Button
    lateinit var like : Button
    lateinit var message : TextView
    val db = Firebase.firestore
    val auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v : View =  inflater.inflate(R.layout.fragment_scan_via_code, container, false)

        monumentName = v.findViewById(R.id.monumentName)
        landmarkCode = v.findViewById(R.id.code)
        view = v.findViewById(R.id.view)
        like = v.findViewById(R.id.like)
        message = v.findViewById(R.id.message)

        val userUID = auth.currentUser?.uid
        if (userUID != null) {
            Log.d("Message", userUID)
        }

        view.setOnClickListener {
            db.collection("monuments").
            document(monumentName.text.toString()).collection("Code").
            get().addOnSuccessListener {result->
                for(document in result){
                    if(document.data["Key"] == monumentName.text.toString() + "_" + landmarkCode.text.toString()){
                        message.text = "You can view the monument now"
                        like.visibility = View.VISIBLE
                    }
                }
            }
        }

        like.setOnClickListener {

            Toast.makeText(activity, "Added to Favourites", Toast.LENGTH_SHORT).show()

            db.collection("users").get().addOnSuccessListener { result->
                for(user in result){
                    if(userUID == user.data["User UID"]){

                        var newArray = ArrayList<String>()
                        newArray.add(monumentName.text.toString())

                        //only for one
                        db.runBatch {batch->
                            batch.update(db.collection("users").document(user.id), "Favourites", newArray)
                        }
                    }
                }

            }
        }


        return v
    }
}