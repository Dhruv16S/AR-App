package com.ar.sihproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class CodeFragment : Fragment() {
    lateinit var register : Button
    lateinit var imageURI : Uri
    lateinit var chooseImage : ImageView
    lateinit var imageName : TextInputEditText
    lateinit var monumentName : TextInputEditText
    val storage = Firebase.storage
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v : View = inflater.inflate(R.layout.fragment_code, container, false)

        chooseImage = v.findViewById(R.id.imageView)
        register = v.findViewById(R.id.register)
        imageName = v.findViewById(R.id.imageName)
        monumentName = v.findViewById(R.id.monumentName)

        chooseImage.setOnClickListener {
            selectImage()
        }

        register.setOnClickListener {
            uploadImage()
        }
        return v
    }

    private fun uploadImage() {

        val storageReference = FirebaseStorage.getInstance().getReference("images/${imageName.text.toString()}")
        storageReference.putFile(imageURI).addOnSuccessListener {

            val mon = hashMapOf(
                "Monument Name" to monumentName.text.toString(),
                "Code" to imageName.text.toString()
            )
            db.collection("monuments").add(mon)

            chooseImage.setImageURI(null)
            chooseImage.setBackgroundResource(R.drawable.reference_image_code)
            imageName.text = null
            monumentName.text = null

            Toast.makeText(activity, "Image Added Successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(activity, "Upload Failed", Toast.LENGTH_SHORT).show()
        }
    }


    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == Activity.RESULT_OK){
            imageURI = data?.data!!
            chooseImage.setImageURI(imageURI)
        }
    }
}