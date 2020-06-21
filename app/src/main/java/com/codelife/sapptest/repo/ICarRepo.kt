package com.codelife.sapptest.repo

import com.codelife.sapptest.models.MakeInfo
import com.codelife.sapptest.models.ModelInfo
import com.codelife.sapptest.models.PriceValuation
import com.codelife.sapptest.models.TrimInfo
import io.reactivex.rxjava3.core.Single

interface ICarRepo {

    fun getMakes(): Single<List<MakeInfo>>

    fun getModels(makeId: String): Single<List<ModelInfo>>

    fun getTrim(makeId: String, modelId: String): Single<List<TrimInfo>>

    fun getPriceValuation(
        makeId: String,
        modelId: String?,
        year: Int,
        trim: String?
    ): Single<PriceValuation>
}