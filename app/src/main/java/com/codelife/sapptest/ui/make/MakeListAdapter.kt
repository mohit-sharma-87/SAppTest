package com.codelife.sapptest.ui.make

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codelife.sapptest.databinding.ItemMakeBinding
import com.codelife.sapptest.models.MakeInfo
import com.codelife.sapptest.utils.ListViewHolder

class MakeListAdapter(private val list: List<MakeInfo>, private val view: MakeFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedItem: MakeInfo? = null

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

        if (list[position].makeId == selectedItem?.makeId) {
            binding.itemMakeSelected.visibility = View.VISIBLE
        } else {
            binding.itemMakeSelected.visibility = View.GONE
        }
    }

    fun updateSelectedValue(selectedMakeInfo: MakeInfo) {
        selectedItem?.let {
            val previousSelectedPosition = list.indexOf(it)
            notifyItemChanged(previousSelectedPosition)
        }

        val currentSelectedPosition = list.indexOf(selectedMakeInfo)
        selectedItem = selectedMakeInfo
        notifyItemChanged(currentSelectedPosition)
    }
}