package com.codelife.sapptest.repo

import com.codelife.sapptest.models.MakeInfo
import com.codelife.sapptest.models.ModelInfo
import com.codelife.sapptest.models.PriceValuation
import com.codelife.sapptest.models.TrimInfo
import com.codelife.sapptest.repo.network.NetworkClientFactory
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
        return webService.getTrims().map {
            it.filter { trimInfo ->
                trimInfo.makeId == makeId && trimInfo.modelMapping.contains(modelId)
            }
        }
    }

    override fun getPriceValuation(
        makeId: String,
        modelId: String?,
        year: Int,
        trim: String?
    ): Single<PriceValuation> {
        return webService.getPriceValuation(
            makeId = makeId,
            year = year,
            modelId = modelId,
            trimId = trim
        )
    }
}