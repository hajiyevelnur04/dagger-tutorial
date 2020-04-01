package com.eebros.daggertutorial

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.eebros.daggertutorial.base.BaseActivity
import com.eebros.daggertutorial.di.ViewModelProviderFactory
import com.eebros.daggertutorial.remote.data.response.GetAllCardResponseModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: MainActivityViewModel

    private val allCards = arrayListOf<GetAllCardResponseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize view model with constructor
        viewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]


        //this helps to invoke and send data to and from viewModel
        setOutputListener()
        setInputListener()

        Log.d("server res:" , allCards.toString())
    }

    private fun setOutputListener() {
        viewModel.outputs.accountsSuccess().subscribe{
            allCards.add(it)
        }.addTo(subscriptions)
    }

    private fun setInputListener() {
        viewModel.inputs.getAllCards()
    }


}
