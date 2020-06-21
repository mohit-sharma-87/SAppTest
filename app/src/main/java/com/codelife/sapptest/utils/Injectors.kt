package com.codelife.sapptest.utils

import com.codelife.sapptest.repo.CarInfoRepo
import com.codelife.sapptest.repo.CarRepo
import com.codelife.sapptest.ui.make.MakeViewModel
import com.codelife.sapptest.ui.make.MakeViewModelFactory
import com.codelife.sapptest.ui.model.ModelViewModel
import com.codelife.sapptest.ui.model.ModelViewModelFactory


object Injectors {

    private fun getCarRepo(): CarInfoRepo {
        return CarRepo()
    }

    fun getMakeViewModel(): MakeViewModel {
        return MakeViewModelFactory(getCarRepo()).create(MakeViewModel::class.java)
    }

    fun getModelViewModel(): ModelViewModel {
        return ModelViewModelFactory(getCarRepo()).create(ModelViewModel::class.java)
    }
}

