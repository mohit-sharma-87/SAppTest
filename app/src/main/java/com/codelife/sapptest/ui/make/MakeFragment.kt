package com.codelife.sapptest.ui.make

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.codelife.sapptest.databinding.FragmentMakeBinding
import com.codelife.sapptest.utils.Injectors

class MakeFragment : Fragment() {

    private lateinit var viewModel: MakeViewModel
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
        viewModel = Injectors.getMakeViewModel()
        observeMakes()
        observeOnErrors()
        getMakes()
    }

    private fun observeOnErrors() {
        viewModel.errorMgs.observe(viewLifecycleOwner, Observer {
            hideLoading()
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    private fun getMakes() {
        showLoading()
        viewBinding.makeRvList.visibility = View.GONE
        viewModel.getMakeInfo()
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
            viewBinding.makeRvList.adapter = MakeListAdapter(it)
        })
    }

}



