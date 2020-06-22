package com.codelife.sapptest.ui.pricevaluation

import androidx.lifecycle.ViewModel
import com.codelife.sapptest.repo.ICarRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PriceValuationViewModel(private val carRepo: ICarRepo) : ViewModel() {

    fun getPriceValuation(makeId: String, modelId: String?, year: Int, trim: String?) {
        carRepo.getPriceValuation(makeId = makeId, modelId = modelId, year = year, trim = trim)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {
                // Log error to error framework in production

                it.printStackTrace()
            })
    }
}