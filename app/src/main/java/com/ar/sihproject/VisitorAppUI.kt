package com.ar.sihproject


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.hitomi.cmlibrary.CircleMenu

class VisitorAppUI : AppCompatActivity() {

    lateinit var cmMain : CircleMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitor_app_ui)

        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()

        val mapsFragment = Maps()
        fragmentTransaction.add(R.id.frame, mapsFragment)

        fragmentTransaction.commit()

        cmMain = findViewById(R.id.cmMain)
        cmMain.setMainMenu(Color.parseColor("#000000"), R.drawable.ic_menu, R.drawable.ic_cancel)

        cmMain.addSubMenu(Color.parseColor("#F5DE61"), R.drawable.ic_home)
        cmMain.addSubMenu(Color.parseColor("#a0cbf1"), R.drawable.ic_info)
        cmMain.addSubMenu(Color.parseColor("#a0cbf1"), R.drawable.ic_info)
        cmMain.addSubMenu(Color.parseColor("#F56161"), R.drawable.ic_favourite)
        cmMain.addSubMenu(Color.parseColor("#BBF561"), R.drawable.ic_contact)
        cmMain.addSubMenu(Color.parseColor("#a0cbf1"), R.drawable.ic_info)


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

                3 -> {
                    //Toast.makeText(this, "This is Favourites", Toast.LENGTH_SHORT).show()
                    val fragmentManagerFavourites : FragmentManager = supportFragmentManager
                    val fragmentTransactionFavourites : FragmentTransaction = fragmentManagerFavourites.beginTransaction()
                    val favouritesFragment = Favourites()
                    fragmentTransactionFavourites.replace(R.id.frame, favouritesFragment)
                    fragmentTransactionFavourites.addToBackStack(null)
                    fragmentTransactionFavourites.commit()
                }

                4 -> {
                    //Toast.makeText(this, "This is Account", Toast.LENGTH_SHORT).show()
                    val fragmentManagerAccount : FragmentManager = supportFragmentManager
                    val fragmentTransactionAccount : FragmentTransaction = fragmentManagerAccount.beginTransaction()
                    val accountFragment = Account()
                    fragmentTransactionAccount.replace(R.id.frame, accountFragment)
                    fragmentTransactionAccount.addToBackStack(null)
                    fragmentTransactionAccount.commit()
                }

                5 -> {
                    //Toast.makeText(this, "This is Scan and View", Toast.LENGTH_SHORT).show()
                    val fragmentManagerScan : FragmentManager = supportFragmentManager
                    val fragmentTransactionScan : FragmentTransaction = fragmentManagerScan.beginTransaction()
                    val scanFragment = ScanAndView()
                    fragmentTransactionScan.replace(R.id.frame, scanFragment)
                    fragmentTransactionScan.addToBackStack(null)
                    fragmentTransactionScan.commit()
                }
                else->{

                }
            }
        }
    }
}