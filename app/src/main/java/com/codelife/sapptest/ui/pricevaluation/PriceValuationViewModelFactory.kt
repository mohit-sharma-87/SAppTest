package com.codelife.sapptest.ui.pricevaluation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codelife.sapptest.repo.ICarRepo

class PriceValuationViewModelFactory(
    private val repository: ICarRepo
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PriceValuationViewModel(repository) as T
    }
}