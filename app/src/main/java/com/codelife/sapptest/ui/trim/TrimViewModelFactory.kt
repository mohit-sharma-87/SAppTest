package com.codelife.sapptest.ui.trim

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codelife.sapptest.repo.ICarRepo

class TrimViewModelFactory(
    private val repository: ICarRepo
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrimViewModel(repository) as T
    }
}