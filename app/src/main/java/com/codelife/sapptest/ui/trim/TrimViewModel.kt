package com.codelife.sapptest.ui.trim

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codelife.sapptest.R
import com.codelife.sapptest.models.TrimInfo
import com.codelife.sapptest.repo.ICarRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class TrimViewModel(private val carRepo: ICarRepo) : ViewModel() {

    val trims = MutableLiveData<List<TrimInfo>>()
    val errorMgs = MutableLiveData<Int>()
    val noElement = MutableLiveData<Boolean>(false)

    @SuppressLint("CheckResult")
    fun getTrims(makeId: String, modelId: String) {
        carRepo
            .getTrim(makeId, modelId)
            .map {
                it
                    .filter { trimInfo ->
                        trimInfo.active
                    }
                    .sortedBy { trimInfo ->
                        trimInfo.trimName
                    }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                trims.value = it
            }, {
                if (it is NoSuchElementException) {
                    noElement.value = true
                } else {
                    errorMgs.value = R.string.error_mgs
                    it.printStackTrace()
                }
            })
    }
}