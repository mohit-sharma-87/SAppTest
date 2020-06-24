package com.codelife.sapptest.ui.model

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codelife.sapptest.R
import com.codelife.sapptest.models.ModelInfo
import com.codelife.sapptest.repo.ICarRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ModelViewModel(private val repo: ICarRepo) : ViewModel() {

    val models = MutableLiveData<List<ModelInfo>>()
    val errorMgs = MutableLiveData<Int>()
    private var cacheKey: String = ""
    val selectedValue = MutableLiveData<ModelInfo>()

    fun getModels(makeId: String) {
        if (cacheKey.isBlank() || cacheKey != makeId) {
            cacheKey = makeId
            getModelsFromRepo(makeId)
        }
    }

    @SuppressLint("CheckResult")
    private fun getModelsFromRepo(makeId: String) {
        repo
            .getModels(makeId)
            .map {
                it.sortedBy { modelInfo ->
                    modelInfo.modelName
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                models.value = it
            }, {
                // Log error to error framework in production
                errorMgs.value = R.string.error_mgs
            })
    }


}