package com.example.mytravelapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mytravelapp.R
import com.example.mytravelapp.data.Attraction
import com.example.mytravelapp.data.AttractionResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.addAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    private lateinit var appBarConfiguration : AppBarConfiguration

    val attractions: List<Attraction> by lazy {
        uploadAttractions()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    fun uploadAttractions(): List<Attraction> {

        val textFromFile =
            resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val adapter : JsonAdapter<AttractionResponse> = moshi.adapter(AttractionResponse::class.java)
        return adapter.fromJson(textFromFile)!!.attractions
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}