package com.example.tecafe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MejaAdapter(private val mejaList: ArrayList<MejaModel>) :
    RecyclerView.Adapter<MejaAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.meja_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMeja = mejaList[position]

        holder.tvMejaName.text = currentMeja.mejaName
        holder.tvMejaPrice.text = currentMeja.mejaPrice
        holder.tvMejaDesc.text = currentMeja.mejaDesc

    }

    override fun getItemCount(): Int {
        return mejaList.size
    }



    class ViewHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val tvMejaName: TextView = itemView.findViewById(R.id.tvMejaName)
        val tvMejaPrice: TextView = itemView.findViewById(R.id.tvMejaPrice)
        val tvMejaDesc: TextView = itemView.findViewById(R.id.tvMejaDesc)


        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}