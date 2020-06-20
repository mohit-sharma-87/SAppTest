package com.codelife.sapptest.ui.pricevaluation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.codelife.sapptest.R

class PriceValuationFragment : Fragment() {

    companion object {
        fun newInstance() =
            PriceValuationFragment()
    }

    private lateinit var viewModel: PriceValuationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.price_valuation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PriceValuationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}