package com.ar.sihproject

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hitomi.cmlibrary.CircleMenu

class AdminAppUI : AppCompatActivity() {

    lateinit var cmMain : CircleMenu
    lateinit var layout : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_app_ui)

        layout = findViewById(R.id.layout)

        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()

        val mapsFragment = Maps()
        fragmentTransaction.add(R.id.frame, mapsFragment)

        fragmentTransaction.commit()

        cmMain = findViewById(R.id.cmMain)
        cmMain.setMainMenu(Color.parseColor("#000000"), R.drawable.ic_menu, R.drawable.ic_cancel)

        cmMain.addSubMenu(Color.parseColor("#F5DE61"), R.drawable.ic_home)
        //dummy buttons
        cmMain.addSubMenu(Color.parseColor("#a0cbf1"), R.drawable.ic_info)
        cmMain.addSubMenu(Color.parseColor("#a0cbf1"), R.drawable.ic_info)
        cmMain.addSubMenu(Color.parseColor("#a0cbf1"), R.drawable.ic_info)

        cmMain.addSubMenu(Color.parseColor("#5033FE"), R.drawable.ic_logout)
        cmMain.addSubMenu(Color.parseColor("#F56161"), R.drawable.ic_favourite)
        cmMain.addSubMenu(Color.parseColor("#BBF561"), R.drawable.ic_contact)
        cmMain.addSubMenu(Color.parseColor("#a0cbf1"), R.drawable.ic_register)



        //adding click listeners to menu items
        cmMain.setOnMenuSelectedListener { it ->
            when (it) {
                0 -> {
                    //Toast.makeText(this, "This is Maps", Toast.LENGTH_SHORT).show()
                    val fragmentManagerMaps : FragmentManager = supportFragmentManager
                    val fragmentTransactionMaps : FragmentTransaction = fragmentManagerMaps.beginTransaction()
                    val mapsFragmentOnClick = Maps()
                    fragmentTransactionMaps.replace(R.id.frame, mapsFragmentOnClick)
                    fragmentTransactionMaps.addToBackStack(null)
                    fragmentTransactionMaps.commit()
                }

                5-> {
                    //Toast.makeText(this, "This is Favourites", Toast.LENGTH_SHORT).show()
                    val fragmentManagerFavourites : FragmentManager = supportFragmentManager
                    val fragmentTransactionFavourites : FragmentTransaction = fragmentManagerFavourites.beginTransaction()
                    val favouritesFragment = Favourites()
                    fragmentTransactionFavourites.replace(R.id.frame, favouritesFragment)
                    fragmentTransactionFavourites.addToBackStack(null)
                    fragmentTransactionFavourites.commit()
                }

                6 -> {
                    //Toast.makeText(this, "This is Account", Toast.LENGTH_SHORT).show()
                    val fragmentManagerAccount : FragmentManager = supportFragmentManager
                    val fragmentTransactionAccount : FragmentTransaction = fragmentManagerAccount.beginTransaction()
                    val accountFragment = Account()
                    fragmentTransactionAccount.replace(R.id.frame, accountFragment)
                    fragmentTransactionAccount.addToBackStack(null)
                    fragmentTransactionAccount.commit()
                }

                7 -> {
                    //Toast.makeText(this, "This is Scan and View", Toast.LENGTH_SHORT).show()
                    val fragmentManagerRegister : FragmentManager = supportFragmentManager
                    val fragmentTransactionRegister : FragmentTransaction = fragmentManagerRegister.beginTransaction()
                    val registerFragment = Register()
                    fragmentTransactionRegister.replace(R.id.frame, registerFragment)
                    fragmentTransactionRegister.addToBackStack(null)
                    fragmentTransactionRegister.commit()
                }
                4 -> {
                    var alert = AlertDialog.Builder(this@AdminAppUI)
                    alert.setTitle("Sign Out?").setMessage("Do you want to Sign out ?").setIcon(R.drawable.prompt)
                        .setCancelable(false).setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                            dialog.cancel()
                        })
                        .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                            Firebase.auth.signOut()
                            val intent = Intent(this@AdminAppUI, MainActivity::class.java)
                            startActivity(intent)
                        })
                    alert.create().show()
                }
                else->{

                }
            }
        }
    }
}