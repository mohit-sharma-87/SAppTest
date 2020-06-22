package com.codelife.sapptest.repo

import com.codelife.sapptest.models.MakeInfo
import com.codelife.sapptest.models.ModelInfo
import com.codelife.sapptest.models.PriceValuation
import com.codelife.sapptest.models.TrimInfo
import com.codelife.sapptest.repo.database.CarInfoDao
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CarRepo(private val networkDao: EndPoints, private val databaseDao: CarInfoDao) : ICarRepo {

    override fun getMakes(): Single<List<MakeInfo>> {
        val getFromNetwork = networkDao.getMakes().toObservable()
        val database = Observable.just(databaseDao.getMakes()).subscribeOn(Schedulers.io())

        return Observable.concat(getFromNetwork, database)
            .subscribeOn(Schedulers.io())
            .firstElement().toSingle()
    }

    override fun getModels(makeId: String): Single<List<ModelInfo>> {
        return networkDao.getModel().map {
            it.filter { modelInfo ->
                modelInfo.makeId == makeId
            }
        }
    }

    override fun getTrim(makeId: String, modelId: String): Single<List<TrimInfo>> {
        return networkDao.getTrims().map {
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
        return networkDao.getPriceValuation(
            makeId = makeId,
            year = year,
            modelId = modelId,
            trimId = trim
        )
    }
}