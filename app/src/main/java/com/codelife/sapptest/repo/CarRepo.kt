package com.codelife.sapptest.repo

import com.codelife.sapptest.dao.ModelInfo
import com.codelife.sapptest.dao.TrimInfo
import com.codelife.sapptest.repo.network.NetworkClientFactory
import com.codelife.sapptest.ui.pricevaluation.make.dto.MakeInfo
import io.reactivex.rxjava3.core.Single

class CarRepo : ICarRepo {

    private val webService by lazy {
        NetworkClientFactory.create()
    }

    override fun getMakes(): Single<List<MakeInfo>> {
        return webService.getMakes()
    }

    override fun getModels(makeId: String): Single<List<ModelInfo>> {
        return webService.getModel().map {
            it.filter { modelInfo ->
                modelInfo.makeId == makeId
            }
        }
    }

    override fun getTrim(makeId: String, modelId: String): Single<List<TrimInfo>> {
        return webService.getTrims()
    }
}