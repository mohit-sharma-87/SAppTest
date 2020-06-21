package com.codelife.sapptest.ui.year

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codelife.sapptest.databinding.ItemYearBinding
import com.codelife.sapptest.utils.ListViewHolder

class YearListAdapter(private val list: List<Int>, private val view: YearFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListViewHolder(ItemYearBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding: ItemYearBinding = (holder as ListViewHolder).binding as ItemYearBinding
        binding.year = list[position]
        binding.view = view
    }

}