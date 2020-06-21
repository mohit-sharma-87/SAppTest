package com.codelife.sapptest.ui.model

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codelife.sapptest.R
import com.codelife.sapptest.dao.ModelInfo
import com.codelife.sapptest.repo.CarInfoRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ModelViewModel(private val repo: CarInfoRepo) : ViewModel() {

    val models = MutableLiveData<List<ModelInfo>>()
    val errorMgs = MutableLiveData<Int>()
    var state: Parcelable? = null

    fun getModels(makeId: String) {
        repo
            .getModels(makeId)
            .map {
                it
                    .filter { modelInfo ->
                        modelInfo.active
                    }
                    .sortedBy { modelInfo ->
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
                it.printStackTrace()
            })
    }


}