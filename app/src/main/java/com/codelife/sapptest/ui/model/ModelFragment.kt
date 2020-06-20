package com.codelife.sapptest.ui.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.codelife.sapptest.R

class ModelFragment : Fragment() {

    companion object {
        fun newInstance() = ModelFragment()
    }

    private lateinit var viewModel: ModelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_model, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ModelViewModel::class.java)
        // TODO: Use the ViewModel
    }

}