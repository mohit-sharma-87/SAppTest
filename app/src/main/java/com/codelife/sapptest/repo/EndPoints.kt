package com.codelife.sapptest.repo

import com.codelife.sapptest.dao.ModelInfo
import com.codelife.sapptest.dao.TrimInfo
import com.codelife.sapptest.ui.pricevaluation.make.dto.MakeInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface EndPoints {

    @GET("static/makes/")
    fun getMakes(): Single<List<MakeInfo>>

    @GET("static/models/")
    fun getModel(): Single<List<ModelInfo>>

    @GET("static/submodels/")
    fun getTrims(): Single<List<TrimInfo>>
}