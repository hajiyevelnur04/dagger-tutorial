package com.eebros.daggertutorial.ui.home

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eebros.daggertutorial.R
import com.eebros.daggertutorial.remote.data.response.GetAllCardResponseModel
import kotlinx.android.synthetic.main.cards_list_view.view.*


class HomeFragmentAdapter(
    private val context: Context,
    private val getAllCards: ArrayList<GetAllCardResponseModel>,
    private val onCLickListener: (position: Int) -> Unit,
    private val listener: MainFragmentAdapterListener,
    private val isCardViewAsList: Boolean
) : RecyclerView.Adapter<HomeFragmentAdapter.MainViewHolder>(), Filterable {

    private var filteredCardList = getAllCards

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(getAllCardResponseModel: GetAllCardResponseModel, context: Context, listener: MainFragmentAdapterListener, clickListeners: (position: Int) -> Unit){
            itemView.iconText.text = getAllCardResponseModel.name
            itemView.desc.text = getAllCardResponseModel.desc
            Glide.with(context).load(getAllCardResponseModel.card_images[0].image_url).into(itemView.icon)
            itemView.type.text = getAllCardResponseModel.type
            itemView.setOnClickListener {
                listener.getCardInfo(getAllCardResponseModel)
                clickListeners.invoke(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            inflater.inflate(
                if(isCardViewAsList) R.layout.cards_list_view else R.layout.cards_grid_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = filteredCardList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(filteredCardList[position], context, listener, onCLickListener)
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val filterResult = arrayListOf<GetAllCardResponseModel>()
                if(TextUtils.isEmpty(charSequence) || charSequence.trim().isEmpty()){
                    filterResult.addAll(getAllCards)
                } else {
                    val searchText = charSequence.toString().toLowerCase().trim()

                    getAllCards.forEach {
                        if (it.name.toLowerCase().contains(searchText)){
                            filterResult.add(it)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filterResult
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                filteredCardList = filterResults.values as ArrayList<GetAllCardResponseModel>
                // refresh the list with filtered data
                notifyDataSetChanged()
            }
        }
    }

    open interface MainFragmentAdapterListener{
        fun getCardInfo(getAllCardResponseModel: GetAllCardResponseModel)
    }

}