package com.codelife.sapptest.ui.year

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.codelife.sapptest.databinding.FragmentYearBinding

class YearFragment : Fragment() {

    private lateinit var viewBinding: FragmentYearBinding
    private val viewModel: YearViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(YearViewModel::class.java)
    }

    private val args by navArgs<YearFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentYearBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = (2020 downTo 1990).toList()
        viewBinding.yearRvList.adapter = YearListAdapter(list, this)
    }

    private fun showLoading() {
        viewBinding.yearLookingUp.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        viewBinding.yearLookingUp.visibility = View.GONE
    }

    fun onItemClick(year: Int) {

        val directions = YearFragmentDirections.actionYearFragmentToPriceValuationFragment(
            args.makeId,
            args.makeName,
            args.modelId,
            args.modelName,
            args.trimId,
            args.trimName,
            year
        )

        findNavController().navigate(directions)
    }

}