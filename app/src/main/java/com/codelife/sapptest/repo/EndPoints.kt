package com.codelife.sapptest.repo

import com.codelife.sapptest.models.MakeInfo
import com.codelife.sapptest.models.ModelInfo
import com.codelife.sapptest.models.PriceValuation
import com.codelife.sapptest.models.TrimInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoints {

    @GET("static/makes/")
    fun getMakes(): Single<List<MakeInfo>>

    @GET("static/models/")
    fun getModel(): Single<List<ModelInfo>>

    @GET("static/submodels/")
    fun getTrims(): Single<List<TrimInfo>>

    @GET("v1/predict/price/")
    fun getPriceValuation(
        @Query("make_id") makeId: String,
        @Query("year") year: Int,
        @Query("model_id") modelId: String?,
        @Query("submodel_id") trimId: String?
    ): Single<PriceValuation>
}