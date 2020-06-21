package com.codelife.sapptest.repo

import com.codelife.sapptest.dao.ModelInfo
import com.codelife.sapptest.dao.TrimInfo
import com.codelife.sapptest.ui.pricevaluation.make.dto.MakeInfo
import io.reactivex.rxjava3.core.Single

interface ICarRepo {

    fun getMakes(): Single<List<MakeInfo>>

    fun getModels(makeId: String): Single<List<ModelInfo>>

    fun getTrim(makeId: String, modelId: String): Single<List<TrimInfo>>
}