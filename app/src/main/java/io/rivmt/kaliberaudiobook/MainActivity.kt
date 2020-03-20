package io.rivmt.kaliberaudiobook

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mFragmentManager: FragmentManager = supportFragmentManager
    private lateinit var mHomeFragment: HomeFragment
    private var mLibraryFragment: LibraryFragment = LibraryFragment()
    private var mMenuFragment: MenuFragment = MenuFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fragment
        mHomeFragment = HomeFragment(contentResolver)

        //Title
        setTitle(R.string.app_name)
        supportActionBar?.hide()
        text_main_title.text = getString(R.string.main_home)

        //Bottom Navigation
        bottom_navigation.setOnNavigationItemSelectedListener (mBottomNavigationSelected)
        bottom_navigation.selectedItemId = R.id.navigation_home
    }

    //Bottom Navigation Bar Selection
    private val mBottomNavigationSelected = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        val transaction:FragmentTransaction = mFragmentManager.beginTransaction()
        when(menuItem.itemId) {
            R.id.navigation_home -> {
                text_main_title.text = getString(R.string.main_home)
                transaction.replace(R.id.fragment_main, mHomeFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_library -> {
                text_main_title.text = getString(R.string.main_library)
                transaction.replace(R.id.fragment_main, mLibraryFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu -> {
                text_main_title.text = getString(R.string.main_menu)
                transaction.replace(R.id.fragment_main, mMenuFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            else -> return@OnNavigationItemSelectedListener false
        }
    }
}
