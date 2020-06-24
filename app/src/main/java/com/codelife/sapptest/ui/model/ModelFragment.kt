package com.codelife.sapptest.ui.model

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.codelife.sapptest.R
import com.codelife.sapptest.databinding.FragmentModelBinding
import com.codelife.sapptest.models.ModelInfo
import com.codelife.sapptest.utils.Injectors

class ModelFragment : Fragment() {

    private val viewModel: ModelViewModel by lazy { Injectors.getModelViewModel(requireContext()) }
    private lateinit var viewBinding: FragmentModelBinding
    private val args: ModelFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        viewBinding = FragmentModelBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getModels()
        observeModels()
        observeOnErrors()
        observeOnModelSelected()
    }

    private fun observeModels() {
        viewModel.models.observe(viewLifecycleOwner, Observer {
            hideLoading()
            viewBinding.modelRvList.adapter = ModelListAdapter(list = it, view = this)
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

    fun onItemClick(modelInfo: ModelInfo) {

        viewModel.selectedValue.value = modelInfo
        goToNextScreen(viewModel.selectedValue.value as ModelInfo)
    }

    private fun goToNextScreen(modelInfo: ModelInfo) {
        val directions = ModelFragmentDirections.actionModelFragmentToTrimFragment(
            args.makeId, args.makeName, modelInfo.modelId, modelInfo.modelName
        )
        findNavController().navigate(directions)
    }

    private fun getModels() {
        showLoading()
        viewModel.getModels(args.makeId)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_skip) {
            navigateToYearSelection()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToYearSelection() {
        val modelName = if (viewModel.selectedValue.value == null) {
            null
        } else {
            (viewModel.selectedValue.value as ModelInfo).modelName
        }

        val modelId = if (viewModel.selectedValue.value == null) {
            null
        } else {
            (viewModel.selectedValue.value as ModelInfo).modelId
        }

        val direction =
            ModelFragmentDirections.actionModelFragmentToYearFragment(
                args.makeId,
                args.makeName,
                modelId,
                modelName,
                null,
                null
            )
        findNavController().navigate(direction)
    }

    private fun observeOnModelSelected() {
        viewModel.selectedValue.observe(viewLifecycleOwner, Observer {
            (viewBinding.modelRvList.adapter as ModelListAdapter).updateSelectedValue(it)
        })
    }
}