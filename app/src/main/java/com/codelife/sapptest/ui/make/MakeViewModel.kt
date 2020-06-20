package com.codelife.sapptest.ui.make

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codelife.sapptest.R
import com.codelife.sapptest.repo.CarInfoRepo
import com.codelife.sapptest.ui.pricevaluation.make.dto.MakeInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MakeViewModel(private val carInfoRepo: CarInfoRepo) : ViewModel() {

    val makes = MutableLiveData<List<MakeInfo>>()
    val errorMgs = MutableLiveData<Int>()

    fun getMakeInfo() {
        carInfoRepo
            .getMakes()
            .map {
                it
                    .filter { makeInfo ->
                        makeInfo.active
                    }
                    .sortedBy { makeInfo ->
                        makeInfo.makeName
                    }
            }

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makes.value = it
            }, {
                // Log error to error framework in production
                errorMgs.value = R.string.error_mgs
            })
    }

}