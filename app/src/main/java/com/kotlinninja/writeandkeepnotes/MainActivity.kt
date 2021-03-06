package com.kotlinninja.writeandkeepnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        // setting up all fragments
        // first attach the id of nav controller from main xml file
        navController = findNavController(R.id.fragmentContainerView)
        // add with the actionbar

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
    // here the navigation setup completed

    override fun onBackPressed() {
        super.onBackPressed()
    }


}
