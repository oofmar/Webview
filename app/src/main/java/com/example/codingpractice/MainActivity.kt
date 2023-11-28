package com.example.codingpractice

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var mainMenuLayout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        // Setup main menu layout and buttons
        mainMenuLayout = findViewById<View>(R.id.main_menu_layout)
        setupButtons()

        // Retrieve the start destination ID from the nav graph
        val startDestinationId = navController.graph.startDestinationId

        // Set up a listener for navigation changes
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Toggle visibility of the main menu based on whether the current destination is the start destination
            mainMenuLayout.visibility = if (destination.id == startDestinationId) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun setupButtons() {
        val buttonBeginner = findViewById<Button>(R.id.button_beginner)
        buttonBeginner.setOnClickListener {
            navController.navigate(R.id.beginnerFragment)
        }

        val buttonIntermediate = findViewById<Button>(R.id.button_intermediate)
        buttonIntermediate.setOnClickListener {
            navController.navigate(R.id.intermediateFragment)
        }
    }

    // Optional: Override onSupportNavigateUp if you have an ActionBar or Toolbar
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}