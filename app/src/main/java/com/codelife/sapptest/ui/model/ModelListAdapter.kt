package com.codelife.sapptest.ui.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codelife.sapptest.databinding.ItemModelBinding
import com.codelife.sapptest.models.ModelInfo
import com.codelife.sapptest.utils.ListViewHolder

class ModelListAdapter(private val list: List<ModelInfo>, private val view: ModelFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListViewHolder(ItemModelBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding: ItemModelBinding = (holder as ListViewHolder).binding as ItemModelBinding
        binding.modelInfo = list[position]
        binding.view = view
    }

}