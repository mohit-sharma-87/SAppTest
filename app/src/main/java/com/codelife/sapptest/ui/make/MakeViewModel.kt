package com.codelife.sapptest.ui.make

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codelife.sapptest.R
import com.codelife.sapptest.models.MakeInfo
import com.codelife.sapptest.repo.ICarRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MakeViewModel(private val carRepo: ICarRepo) : ViewModel() {

    val makes = MutableLiveData<List<MakeInfo>>()
    val errorMgs = MutableLiveData<Int>()
    var state: Parcelable? = null


    fun getMakeInfo() {
        carRepo
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
                it.printStackTrace()
                errorMgs.value = R.string.error_mgs
            })
    }

}