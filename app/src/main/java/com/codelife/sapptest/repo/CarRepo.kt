package com.codelife.sapptest.repo

import com.codelife.sapptest.models.MakeInfo
import com.codelife.sapptest.models.ModelInfo
import com.codelife.sapptest.models.PriceValuation
import com.codelife.sapptest.models.TrimInfo
import com.codelife.sapptest.repo.database.CarInfoDao
import io.reactivex.Observable
import io.reactivex.Single

class CarRepo(private val networkDao: EndPoints, private val databaseDao: CarInfoDao) : ICarRepo {

    override fun getMakes(): Single<List<MakeInfo>> {
        val getFromNetwork = getMakeFromNetwork().toObservable()
        val getFromDB = databaseDao.getMakes().toObservable()

        return Observable.concat(getFromDB, getFromNetwork)
            .filter { it.isNotEmpty() }
            .onErrorResumeNext(getFromNetwork)
            .firstElement()
            .toSingle()
    }

    override fun getModels(makeId: String): Single<List<ModelInfo>> {
        val getFromNetwork = getModelFromNetwork(makeId).toObservable()
        val getFromDB = databaseDao.getModels(makeId).toObservable()

        return Observable.concat(getFromDB, getFromNetwork)
            .filter { it.isNotEmpty() }
            .firstElement()
            .toSingle()
    }

    override fun getTrim(makeId: String, modelId: String): Single<List<TrimInfo>> {
        val getFromNetwork = getTrimFromNetwork(makeId, modelId).toObservable()
        val getFromDB = databaseDao.getTrims(makeId, "%$modelId%").toObservable()

        return Observable.concat(getFromDB, getFromNetwork)
            .filter { it.isNotEmpty() }
            .firstElement()
            .toSingle()
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


    private fun getMakeFromNetwork(): Single<List<MakeInfo>> {
        return networkDao.getMakes()
            .map { it.filter { makeInfo -> makeInfo.active } }
            .doOnSuccess(databaseDao::addUpdateMake)
    }

    private fun getModelFromNetwork(makeId: String): Single<List<ModelInfo>> {
        return networkDao.getModel()
            .map { it.filter { modelInfo -> modelInfo.active } }
            .doOnSuccess(databaseDao::addUpdateModel)
            .map { it.filter { modelInfo -> modelInfo.makeId == makeId } }
    }

    private fun getTrimFromNetwork(makeId: String, modelId: String): Single<List<TrimInfo>> {
        return networkDao.getTrims()
            .map { it.filter { trimInfo -> trimInfo.active } }
            .doOnSuccess(databaseDao::addUpdateTrim)
            .map {
                it.filter { trimInfo ->
                    trimInfo.makeId == makeId && trimInfo.modelMapping.contains(modelId)
                }
            }
    }

}