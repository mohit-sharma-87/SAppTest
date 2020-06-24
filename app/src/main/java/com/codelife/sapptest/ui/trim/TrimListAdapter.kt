package com.codelife.sapptest.ui.trim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codelife.sapptest.databinding.ItemTrimsBinding
import com.codelife.sapptest.models.TrimInfo
import com.codelife.sapptest.utils.ListViewHolder

class TrimListAdapter(private val list: List<TrimInfo>, private val view: TrimFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedItem: TrimInfo? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListViewHolder(ItemTrimsBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding: ItemTrimsBinding = (holder as ListViewHolder).binding as ItemTrimsBinding
        binding.trimInfo = list[position]
        binding.view = view

        if (list[position].trimId == selectedItem?.trimId) {
            binding.itemMakeSelected.visibility = View.VISIBLE
        } else {
            binding.itemMakeSelected.visibility = View.GONE
        }
    }

    fun updateSelectedValue(selectedInfo: TrimInfo) {
        selectedItem?.let {
            val previousSelectedPosition = list.indexOf(it)
            notifyItemChanged(previousSelectedPosition)
        }

        val currentSelectedPosition = list.indexOf(selectedInfo)
        selectedItem = selectedInfo
        notifyItemChanged(currentSelectedPosition)
    }
}