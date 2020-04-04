package com.eebros.daggertutorial.presentation.activity

import android.os.Bundle
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
    }
}
