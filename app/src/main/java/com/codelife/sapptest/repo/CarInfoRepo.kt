package com.codelife.sapptest.repo

import com.codelife.sapptest.dao.ModelInfo
import com.codelife.sapptest.ui.pricevaluation.make.dto.MakeInfo
import io.reactivex.rxjava3.core.Single

interface CarInfoRepo {

    fun getMakes(): Single<List<MakeInfo>>

    fun getModels(makeId: String): Single<List<ModelInfo>>
}