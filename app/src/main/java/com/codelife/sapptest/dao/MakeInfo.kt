package com.codelife.sapptest.dao

import com.google.gson.annotations.SerializedName
import java.util.*

data class MakeInfo(
    @SerializedName("active") val active: Boolean,
    @SerializedName("id") val makeId: String,
    @SerializedName("name") val makeName: String,
    @SerializedName("logo_uri") val logoImageUrl: String,
    @SerializedName("updated_at") val _updatedDate: Date
)