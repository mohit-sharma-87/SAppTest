package com.codelife.sapptest.ui.trim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.codelife.sapptest.dao.TrimInfo
import com.codelife.sapptest.databinding.FragmentModelBinding
import com.codelife.sapptest.ui.model.ModelFragmentArgs
import com.codelife.sapptest.utils.Injectors

class TrimFragment : Fragment() {

    private val viewModel: TrimViewModel by lazy { Injectors.getTrimViewModel() }
    private lateinit var viewBinding: FragmentModelBinding
    private val args: ModelFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentModelBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTrims()
        observeModels()
        observeOnErrors()
    }

    private fun observeModels() {
        viewModel.trims.observe(viewLifecycleOwner, Observer {
            hideLoading()
            viewBinding.modelRvList.adapter = TrimListAdapter(list = it, view = this)
        })
    }

    private fun observeOnErrors() {
        viewModel.errorMgs.observe(viewLifecycleOwner, Observer {
            hideLoading()
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    private fun showLoading() {
        viewBinding.modelLookingUp.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        viewBinding.modelLookingUp.visibility = View.GONE
    }

    fun onItemClick(trimInfo: TrimInfo) {

    }

    private fun getTrims() {
        showLoading()

    }

}