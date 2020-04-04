package com.eebros.daggertutorial.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.eebros.daggertutorial.R
import com.eebros.daggertutorial.base.BaseFragment
import com.eebros.daggertutorial.di.ViewModelProviderFactory
import javax.inject.Inject

class TestFragment : BaseFragment(){

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: TestFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)
        viewModel = ViewModelProvider(this, factory)[TestFragmentViewModel::class.java]
        return view
    }
}