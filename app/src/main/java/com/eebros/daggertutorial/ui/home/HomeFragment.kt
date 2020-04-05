package com.eebros.daggertutorial.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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


class HomeFragment : BaseFragment(),
    HomeFragmentAdapter.MainFragmentAdapterListener {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: HomeFragmentViewModel

    lateinit var mainFragmentAdapter: HomeFragmentAdapter

    private val allCards = arrayListOf<GetAllCardResponseModel>()

    lateinit var dialog: CustomProgressDialog

    lateinit var cardContainer: RecyclerView

    private var isCarViewList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initView(view)

        //initialize view model with constructor
        viewModel = ViewModelProvider(this, factory)[HomeFragmentViewModel::class.java]

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
            HomeFragmentAdapter(
                requireContext(),
                allCards,
                {

                },
                this,
                isCarViewList
            )
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
            cardContainer.adapter?.notifyDataSetChanged()
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


        val cardListView = menu.findItem(R.id.list_view)
        cardListView.setOnMenuItemClickListener {
            var layoutManager: RecyclerView.LayoutManager = if (isCarViewList) {
                cardListView.setIcon(R.drawable.ic_grid)
                LinearLayoutManager(requireActivity())
            } else {
                cardListView.setIcon(R.drawable.ic_list)
                GridLayoutManager(requireActivity(), 2)
            }
            isCarViewList = !isCarViewList
            cardContainer.layoutManager = layoutManager
            cardContainer.adapter = mainFragmentAdapter

            mainFragmentAdapter =
                HomeFragmentAdapter(
                    requireContext(),
                    allCards,
                    {

                    },
                    this,
                    isCarViewList
                )
            mainFragmentAdapter.notifyDataSetChanged()
            true
        }

    }

    override fun getCardInfo(getAllCardResponseModel: GetAllCardResponseModel) {
        var intent = Intent(requireActivity(), SelectedCardActivity::class.java)
        intent.putExtra("name", getAllCardResponseModel.name)
        intent.putExtra("desc", getAllCardResponseModel.desc)
        intent.putExtra("name", getAllCardResponseModel.type)
        intent.putExtra("image",getAllCardResponseModel.card_images[0].image_url)
        intent.putExtra("race", getAllCardResponseModel.race)
        intent.putExtra("archetype", getAllCardResponseModel.archetype)
        intent.putExtra("id", getAllCardResponseModel.id)
        intent.putExtra("setName", getAllCardResponseModel.card_sets[0].set_name)
        intent.putExtra("setCode", getAllCardResponseModel.card_sets[0].set_code)
        intent.putExtra("setPrice", getAllCardResponseModel.card_sets[0].set_price)
        startActivity(intent)
    }
}
