package com.eebros.daggertutorial

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.eebros.daggertutorial.base.BaseActivity
import com.eebros.daggertutorial.di.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize view model with constructor
        viewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]
    }
}
