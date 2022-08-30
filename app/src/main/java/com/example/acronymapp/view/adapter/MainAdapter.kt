package com.example.acronymapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapp.R
import com.example.acronymapp.model.Lfs
import kotlinx.android.synthetic.main.main_item_row.view.*

class MainAdapter() : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    private var data : ArrayList<Lfs>?=null

    fun setData(list: ArrayList<Lfs>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item_row, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(item: Lfs?) {
            if (item != null) {
                itemView.txt_lf.text = item.lf
                itemView.txt_freq.text = item.freq.toString()
                itemView.txt_since.text = item.since.toString()
            }
        }
    }
}