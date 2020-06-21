package com.codelife.sapptest.dao

import com.google.gson.annotations.SerializedName

data class PriceValuation(
    @SerializedName("currency") val currency: String,
    @SerializedName("result") val price: String
)