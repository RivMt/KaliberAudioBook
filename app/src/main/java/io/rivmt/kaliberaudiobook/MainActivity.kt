package io.rivmt.kaliberaudiobook

import android.content.ContentResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //private val mAudioDataControl:AudioDataControl = AudioDataControl(contentResolver)

    private val mFragmentManager: FragmentManager = supportFragmentManager
    private var mHomeFragment: HomeFragment = HomeFragment()
    private val mFragmentTransaction:FragmentTransaction = mFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Title
        setTitle(R.string.app_name)
        supportActionBar?.hide()
        text_main_title.text = getString(R.string.main_home)

        //Bottom Navigation
        bottom_navigation.setOnNavigationItemSelectedListener (mBottomNavigationSelected)
        bottom_navigation.selectedItemId = R.id.navigation_home

        //Fragment
        mFragmentTransaction.replace(R.id.fragment_main, mHomeFragment).commitAllowingStateLoss()
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
