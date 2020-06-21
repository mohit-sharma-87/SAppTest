package com.codelife.sapptest.ui.make

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.codelife.sapptest.R
import com.codelife.sapptest.databinding.FragmentMakeBinding
import com.codelife.sapptest.repo.CarRepo
import com.codelife.sapptest.ui.pricevaluation.make.dto.MakeInfo

class MakeFragment : Fragment() {


    private val viewModel by navGraphViewModels<MakeViewModel>(R.id.mobile_navigation) {
        MakeViewModelFactory(CarRepo())
    }

    private lateinit var viewBinding: FragmentMakeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMakeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeMakes()
        observeOnErrors()

        viewModel.state?.let {
            (viewBinding.makeRvList.layoutManager as LinearLayoutManager).onRestoreInstanceState(it)
        }

        if (viewModel.state == null) {
            getMakes()
        }
    }

    private fun getMakes() {
        showLoading()
        viewBinding.makeRvList.visibility = View.GONE
        viewModel.getMakeInfo()
    }

    private fun observeOnErrors() {
        viewModel.errorMgs.observe(viewLifecycleOwner, Observer {
            hideLoading()
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    private fun showLoading() {
        viewBinding.makeLookingUp.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        viewBinding.makeLookingUp.visibility = View.GONE
    }

    private fun observeMakes() {
        viewModel.makes.observe(viewLifecycleOwner, Observer {
            hideLoading()
            viewBinding.makeRvList.visibility = View.VISIBLE
            viewBinding.makeRvList.adapter = MakeListAdapter(it, this)
        })
    }

    fun onItemClick(makeInfo: MakeInfo) {
        val navigateToModel =
            MakeFragmentDirections.actionToModelFragment(makeInfo.makeId, makeInfo.makeName)
        findNavController().navigate(navigateToModel)
    }

    override fun onPause() {
        super.onPause()
        viewModel.state =
            (viewBinding.makeRvList.layoutManager as LinearLayoutManager).onSaveInstanceState()
    }

}


