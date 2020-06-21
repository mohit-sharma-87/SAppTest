package com.codelife.sapptest.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codelife.sapptest.repo.ICarRepo

class ModelViewModelFactory(
    private val repository: ICarRepo
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ModelViewModel(repository) as T
    }
}