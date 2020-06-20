package com.codelife.sapptest.utils

import com.codelife.sapptest.repo.CarInfoRepo
import com.codelife.sapptest.repo.CarRepo
import com.codelife.sapptest.ui.make.MakeViewModel
import com.codelife.sapptest.ui.make.MakeViewModelFactory


object Injectors {

    private fun getCarRepo(): CarInfoRepo {
        return CarRepo()
    }

    fun getMakeViewModel(): MakeViewModel {
        return MakeViewModelFactory(getCarRepo()).create(MakeViewModel::class.java)
    }

}

