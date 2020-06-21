package com.codelife.sapptest.ui.make

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codelife.sapptest.repo.ICarRepo

class MakeViewModelFactory(
    private val repository: ICarRepo
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MakeViewModel(repository) as T
    }
}