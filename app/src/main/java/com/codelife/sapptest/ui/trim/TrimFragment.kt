package com.codelife.sapptest.ui.trim

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.codelife.sapptest.R
import com.codelife.sapptest.databinding.FragmentTrimBinding
import com.codelife.sapptest.models.TrimInfo
import com.codelife.sapptest.utils.Injectors

class TrimFragment : Fragment() {

    private val viewModel: TrimViewModel by lazy { Injectors.getTrimViewModel(requireContext()) }
    private lateinit var viewBinding: FragmentTrimBinding
    private val args: TrimFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        viewBinding = FragmentTrimBinding.inflate(inflater, container, false)
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
            viewBinding.trimRvList.adapter = TrimListAdapter(list = it, view = this)
        })
    }

    private fun observeOnErrors() {
        viewModel.errorMgs.observe(viewLifecycleOwner, Observer {
            hideLoading()
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    private fun showLoading() {
        viewBinding.trimLookingUp.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        viewBinding.trimLookingUp.visibility = View.GONE
    }

    fun onItemClick(trimInfo: TrimInfo) {
        val directions = TrimFragmentDirections.actionTrimFragmentToYearFragment(
            args.makeId, args.modelId, trimInfo.trimId
        )

        findNavController().navigate(directions)
    }

    private fun getTrims() {
        showLoading()
        viewModel.getTrims(args.makeId, args.modelId)
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
        val direction =
            TrimFragmentDirections.actionTrimFragmentToYearFragment(args.makeId, args.modelId, null)
        findNavController().navigate(direction)
    }

}