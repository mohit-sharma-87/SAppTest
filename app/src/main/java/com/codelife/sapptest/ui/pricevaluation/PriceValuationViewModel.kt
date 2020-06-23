package com.codelife.sapptest.ui.pricevaluation

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codelife.sapptest.R
import com.codelife.sapptest.repo.ICarRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PriceValuationViewModel(private val carRepo: ICarRepo) : ViewModel() {

    val priceValue = MutableLiveData<String>()
    val errorMgs = MutableLiveData<Int>()

    @SuppressLint("CheckResult")
    fun getPriceValuation(makeId: String, modelId: String?, year: Int, trim: String?) {
        carRepo.getPriceValuation(makeId = makeId, modelId = modelId, year = year, trim = trim)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                priceValue.value = it.currency + " " + it.price
            }, {
                errorMgs.value = R.string.error_mgs
                it.printStackTrace()
            })
    }
}