package com.codelife.sapptest.ui.pricevaluation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.codelife.sapptest.R
import com.codelife.sapptest.utils.Injectors

class PriceValuationFragment : Fragment() {

    private val viewModel: PriceValuationViewModel by lazy {
        Injectors.getPriceValuationViewModel(requireContext())
    }

    private val args by navArgs<PriceValuationFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.price_valuation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPriceValuation(
            makeId = args.makeId,
            modelId = args.modelId,
            year = args.year,
            trim = args.trimId
        )
    }

}