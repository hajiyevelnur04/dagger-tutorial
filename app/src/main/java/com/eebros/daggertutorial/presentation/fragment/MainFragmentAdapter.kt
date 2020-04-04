package com.eebros.daggertutorial.presentation.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eebros.daggertutorial.R
import com.eebros.daggertutorial.remote.data.response.GetAllCardResponseModel
import kotlinx.android.synthetic.main.cards_list_view.view.*

class MainFragmentAdapter(private val context:Context,
                          private val getAllCards: ArrayList<GetAllCardResponseModel>,
                          private val onCLickListener: (position:Int) -> Unit
) : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(getAllCardResponseModel: GetAllCardResponseModel, context: Context, clickListeners: (position: Int) -> Unit){
            itemView.iconText.text = getAllCardResponseModel.name
            Glide.with(context).load(getAllCardResponseModel.card_images[0].image_url).into(itemView.icon);
            itemView.setOnClickListener { clickListeners.invoke(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            inflater.inflate(
                R.layout.cards_list_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = getAllCards.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getAllCards[position], context, onCLickListener)
    }
}