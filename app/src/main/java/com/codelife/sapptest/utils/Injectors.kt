package com.codelife.sapptest.utils

import com.codelife.sapptest.repo.CarRepo
import com.codelife.sapptest.repo.ICarRepo
import com.codelife.sapptest.ui.make.MakeViewModel
import com.codelife.sapptest.ui.make.MakeViewModelFactory
import com.codelife.sapptest.ui.model.ModelViewModel
import com.codelife.sapptest.ui.model.ModelViewModelFactory
import com.codelife.sapptest.ui.pricevaluation.PriceValuationViewModel
import com.codelife.sapptest.ui.pricevaluation.PriceValuationViewModelFactory
import com.codelife.sapptest.ui.trim.TrimViewModel
import com.codelife.sapptest.ui.trim.TrimViewModelFactory


object Injectors {

    private fun getCarRepo(): ICarRepo {
        return CarRepo()
    }

    fun getMakeViewModel(): MakeViewModel {
        return MakeViewModelFactory(getCarRepo()).create(MakeViewModel::class.java)
    }

    fun getModelViewModel(): ModelViewModel {
        return ModelViewModelFactory(getCarRepo()).create(ModelViewModel::class.java)
    }

    fun getTrimViewModel(): TrimViewModel {
        return TrimViewModelFactory(getCarRepo()).create(TrimViewModel::class.java)
    }

    fun getPriceValuationViewModel(): PriceValuationViewModel {
        return PriceValuationViewModelFactory(getCarRepo()).create(PriceValuationViewModel::class.java)
    }
}
