package com.eebros.daggertutorial

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.eebros.daggertutorial.base.BaseActivity
import com.eebros.daggertutorial.di.ViewModelProviderFactory
import com.eebros.daggertutorial.remote.data.response.GetAllCardResponseModel
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.math.roundToInt


class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: MainActivityViewModel

    lateinit var mainActivityAdapter: MainActivityAdapter

    private val allCards = arrayListOf<GetAllCardResponseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add this for some SVGs sources seem to not be fully supported
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //initialize view model with constructor
        viewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]

        val glm = GridLayoutManager(this, 2)
        //llm.orientation = LinearLayoutManager.VERTICAL
        cardContainer.layoutManager = glm
        cardContainer.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10), true))
        cardContainer.itemAnimator = DefaultItemAnimator()

        mainActivityAdapter = MainActivityAdapter(applicationContext,allCards){
            Toast.makeText(applicationContext,"$it position", Toast.LENGTH_LONG).show()
        }
        cardContainer.adapter = mainActivityAdapter

        //this helps to invoke and send data to and from viewModel
        setOutputListener()
        setInputListener()

    }

    private fun setOutputListener() {
        viewModel.outputs.accountsSuccess().subscribe{
            allCards.clear()
            allCards.addAll(it)
            mainActivityAdapter.notifyDataSetChanged()
        }.addTo(subscriptions)
    }

    private fun setInputListener() {
        viewModel.inputs.getAllCards()
    }

    private fun dpToPx(dp: Int): Int {
        val r: Resources = resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        ).roundToInt()
    }

}
