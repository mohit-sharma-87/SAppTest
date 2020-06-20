package com.codelife.sapptest.repo

import com.codelife.sapptest.ui.pricevaluation.make.dto.MakeInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface EndPoints {

    @GET("static/makes/")
    fun getModels(): Single<List<MakeInfo>>


}