package com.eebros.daggertutorial.presentation.fragment

import android.app.SearchManager
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eebros.daggertutorial.R
import com.eebros.daggertutorial.base.BaseFragment
import com.eebros.daggertutorial.decor.GridSpacingItemDecoration
import com.eebros.daggertutorial.di.ViewModelProviderFactory
import com.eebros.daggertutorial.remote.data.response.GetAllCardResponseModel
import com.eebros.daggertutorial.view.CustomProgressDialog
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject
import kotlin.math.roundToInt


class MainFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: MainFragmentViewModel

    lateinit var mainFragmentAdapter: MainFragmentAdapter

    private val allCards = arrayListOf<GetAllCardResponseModel>()

    lateinit var dialog: CustomProgressDialog

    lateinit var cardContainer: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        initView(view)

        //initialize view model with constructor
        viewModel = ViewModelProvider(this, factory)[MainFragmentViewModel::class.java]

        val glm = GridLayoutManager(requireActivity(), 2)
        //llm.orientation = LinearLayoutManager.VERTICAL
        cardContainer.layoutManager = glm
        cardContainer.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                dpToPx(10),
                true
            )
        )
        cardContainer.itemAnimator = DefaultItemAnimator()

        mainFragmentAdapter =
            MainFragmentAdapter(
                requireContext(),
                allCards
            ) {
                Toast.makeText(requireContext(), "$it position", Toast.LENGTH_LONG).show()
            }
        cardContainer.adapter = mainFragmentAdapter

        //this helps to invoke and send data to and from viewModel
        setOutputListener()
        setInputListener()
        return view
    }

    private fun initView(view: View) {
        cardContainer = view.findViewById(R.id.cardContainer)
        dialog = CustomProgressDialog(
            requireActivity()
        )
    }

    private fun setOutputListener() {
        viewModel.outputs.accountsSuccess().subscribe{
            allCards.clear()
            allCards.addAll(it)
            mainFragmentAdapter.notifyDataSetChanged()
        }.addTo(subscriptions)

        viewModel.outputs.showProgress().subscribe{

            if(it) dialog.showDialog() else dialog.hideDialog()

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

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.maxWidth = Integer.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("query:" , query)
                mainFragmentAdapter.filter?.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("newText:" , newText)
                mainFragmentAdapter.filter?.filter(newText)
                return true
            }
        })
    }
}
