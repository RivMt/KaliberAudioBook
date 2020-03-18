package io.rivmt.kaliberaudiobook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Title
        setTitle(R.string.app_name)
        supportActionBar?.hide()

        //Bottom Navigation
        bottom_navigation.setOnNavigationItemSelectedListener (mBottomNavigationSelected)
        bottom_navigation.selectedItemId = R.id.navigation_home
    }

    //Bottom Navigation Bar Selection
    private val mBottomNavigationSelected = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when(menuItem.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_library -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu -> {
                return@OnNavigationItemSelectedListener true
            }
            else -> return@OnNavigationItemSelectedListener false
        }
    }
}
