package com.codelife.sapptest.repo

import com.codelife.sapptest.repo.network.NetworkClientFactory
import com.codelife.sapptest.ui.pricevaluation.make.dto.MakeInfo
import io.reactivex.rxjava3.core.Single

class CarRepo : CarInfoRepo {

    private val webService by lazy {
        NetworkClientFactory.create()
    }


    override fun getMakes(): Single<List<MakeInfo>> {
        return webService.getModels()
    }

    override fun getModels(makeId: String) {
        TODO("Not yet implemented")
    }
}