package com.eebros.daggertutorial

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eebros.daggertutorial.base.BaseActivity
import com.eebros.daggertutorial.di.ViewModelProviderFactory
import com.eebros.daggertutorial.ui.dashboard.DashboardFragment
import com.eebros.daggertutorial.ui.home.HomeFragment
import com.eebros.daggertutorial.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(){

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: MainActivityViewModel

    private var searchItem: MenuItem? = null
    private var listView: MenuItem? = null

    private var searchShow = true
    private var cardListShow = true

    private var isCardList = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]

        setSupportActionBar(home_screen_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = getString(R.string.app_name)

        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment()).commit()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.navigation_home -> {
                    searchShow = true
                    cardListShow = true
                    transaction.replace(R.id.nav_host_fragment, HomeFragment()).commit()
                }
                R.id.navigation_dashboard -> {
                    searchShow = false
                    cardListShow = false
                    transaction.replace(R.id.nav_host_fragment, DashboardFragment()).commit()
                }
                R.id.navigation_notifications -> {
                    searchShow = false
                    cardListShow = false
                    transaction.replace(R.id.nav_host_fragment, NotificationsFragment()).commit()
                }
            }
            true
        }


    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.getItem(0)?.isVisible = searchShow
        menu?.getItem(1)?.isVisible = cardListShow
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchItem = menu?.findItem(R.id.action_search)
        listView = menu?.findItem(R.id.list_view)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
