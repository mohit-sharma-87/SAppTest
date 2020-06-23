package com.codelife.sapptest.ui.pricevaluation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.codelife.sapptest.R
import com.codelife.sapptest.databinding.PriceValuationFragmentBinding
import com.codelife.sapptest.utils.Injectors

class PriceValuationFragment : Fragment() {

    private lateinit var binding: PriceValuationFragmentBinding
    private val viewModel: PriceValuationViewModel by lazy {
        Injectors.getPriceValuationViewModel(requireContext())
    }

    private val args by navArgs<PriceValuationFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = PriceValuationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.make = args.makeName

        binding.model = if (args.modelName.isNullOrEmpty()) {
            getString(R.string.default_value)
        } else {
            args.modelName
        }

        binding.trim = if (args.trimName.isNullOrEmpty()) {
            getString(R.string.default_value)
        } else {
            args.trimName
        }

        binding.year = args.year.toString()

        viewModel.getPriceValuation(
            makeId = args.makeId,
            modelId = args.modelId,
            year = args.year,
            trim = args.trimId
        )

        observePriceValue()
        observeOnErrors()
    }

    private fun observePriceValue() {
        viewModel.priceValue.observe(viewLifecycleOwner, Observer {
            binding.price = it
        })
    }


    private fun observeOnErrors() {
        viewModel.errorMgs.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

}