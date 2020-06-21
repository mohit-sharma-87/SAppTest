package com.codelife.sapptest.ui.make

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codelife.sapptest.databinding.ItemMakeBinding
import com.codelife.sapptest.ui.pricevaluation.make.dto.MakeInfo
import com.codelife.sapptest.utils.ListViewHolder

class MakeListAdapter(private val list: List<MakeInfo>, private val view: MakeFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListViewHolder(ItemMakeBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding: ItemMakeBinding = (holder as ListViewHolder).binding as ItemMakeBinding
        binding.makeInfo = list[position]
        binding.view = view
    }

}