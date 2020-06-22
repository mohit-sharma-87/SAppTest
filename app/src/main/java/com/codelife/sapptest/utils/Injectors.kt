package com.codelife.sapptest.utils

import android.content.Context
import com.codelife.sapptest.repo.CarRepo
import com.codelife.sapptest.repo.ICarRepo
import com.codelife.sapptest.repo.database.AppDatabase
import com.codelife.sapptest.repo.database.CarInfoDao
import com.codelife.sapptest.repo.network.NetworkClientFactory
import com.codelife.sapptest.ui.make.MakeViewModel
import com.codelife.sapptest.ui.make.MakeViewModelFactory
import com.codelife.sapptest.ui.model.ModelViewModel
import com.codelife.sapptest.ui.model.ModelViewModelFactory
import com.codelife.sapptest.ui.pricevaluation.PriceValuationViewModel
import com.codelife.sapptest.ui.pricevaluation.PriceValuationViewModelFactory
import com.codelife.sapptest.ui.trim.TrimViewModel
import com.codelife.sapptest.ui.trim.TrimViewModelFactory


object Injectors {

    private val getNetworkDao by lazy { NetworkClientFactory.create() }

    private fun getDatabaseDao(context: Context): CarInfoDao {
        return AppDatabase.getDatabase(context).carInfoDao()
    }

    fun getCarRepo(context: Context): ICarRepo {
        return CarRepo(getNetworkDao, getDatabaseDao(context))
    }

    fun getMakeViewModel(context: Context): MakeViewModel {
        return MakeViewModelFactory(getCarRepo(context)).create(MakeViewModel::class.java)
    }

    fun getModelViewModel(context: Context): ModelViewModel {
        return ModelViewModelFactory(getCarRepo(context)).create(ModelViewModel::class.java)
    }

    fun getTrimViewModel(context: Context): TrimViewModel {
        return TrimViewModelFactory(getCarRepo(context)).create(TrimViewModel::class.java)
    }

    fun getPriceValuationViewModel(context: Context): PriceValuationViewModel {
        return PriceValuationViewModelFactory(getCarRepo(context)).create(PriceValuationViewModel::class.java)
    }
}
