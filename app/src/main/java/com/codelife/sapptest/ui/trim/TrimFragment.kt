package com.codelife.sapptest.ui.trim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.codelife.sapptest.R

class TrimFragment : Fragment() {

    companion object {
        fun newInstance() = TrimFragment()
    }

    private lateinit var viewModel: TrimViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trim, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrimViewModel::class.java)
        // TODO: Use the ViewModel
    }

}