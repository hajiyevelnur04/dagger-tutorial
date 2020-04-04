package com.eebros.daggertutorial.presentation.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.eebros.daggertutorial.R
import com.eebros.daggertutorial.base.BaseActivity
import com.eebros.daggertutorial.di.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: MainActivityViewModel

    private var searchItem: MenuItem? = null

    private var searchShow = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]

        view_pager.adapter =
            MainSelectPagerAdapter(
                this,
                supportFragmentManager
            )
        tabs.setupWithViewPager(view_pager)

        setSupportActionBar(home_screen_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = getString(R.string.app_name)


    }

    private fun onTabSelected(position: Int) {
        view_pager.currentItem = position
        if (position == 0) {
            searchShow = true
        } else if (position == 1) {
            searchShow = false
        }
    }
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.getItem(0)?.isVisible = searchShow
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchItem = menu?.findItem(R.id.action_search)
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
