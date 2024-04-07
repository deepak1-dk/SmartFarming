package com.example.smartfarmingapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.smartfarmingapplication.fragment.CalendarFragment
import com.example.smartfarmingapplication.fragment.CommunityFragment
import com.example.smartfarmingapplication.fragment.CropFragment
import com.example.smartfarmingapplication.fragment.HomeFragment
import com.example.smartfarmingapplication.fragment.ShopFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationDrawer: NavigationView
    private lateinit var fragmentManager: FragmentManager
    private lateinit var toolbar : Toolbar
    @SuppressLint("RestrictedApi")
    private lateinit var bottomNavigationView: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets*/

        // Set the custom toolbar as the action bar
        toolbar = findViewById(R.id.custom_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "AgriApp"


        bottomNavigationView = findViewById(R.id.bottom_navigation)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationDrawer = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationDrawer.setNavigationItemSelectedListener(this)

        bottomNavigationView.background = null
        bottomNavigationView.setOnItemSelectedListener {item ->
            when(item.itemId){
                R.id.home -> openFragment(HomeFragment())
                R.id.shop -> openFragment(ShopFragment())
                R.id.calendar -> openFragment(CalendarFragment())
                R.id.community -> openFragment(CommunityFragment())
                R.id.crop_practices -> openFragment(CropFragment())
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    // To handel the drawer navigation item
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.myOrder -> Toast.makeText(this,"This is My Order",Toast.LENGTH_LONG).show()
            R.id.myDrone -> Toast.makeText(this,"This is My Drone Order",Toast.LENGTH_LONG).show()
            R.id.myAddress -> Toast.makeText(this,"This is My Address",Toast.LENGTH_LONG).show()
            R.id.mySoil -> Toast.makeText(this,"This is My Soil",Toast.LENGTH_LONG).show()
            R.id.contact -> Toast.makeText(this,"This is My Contact",Toast.LENGTH_LONG).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }


}