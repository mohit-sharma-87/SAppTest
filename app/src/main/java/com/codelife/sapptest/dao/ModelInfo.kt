package com.codelife.sapptest.dao

import com.google.gson.annotations.SerializedName
import java.util.*

data class ModelInfo(
    @SerializedName("active") val active: Boolean,
    @SerializedName("id") val modelId: String,
    @SerializedName("make_id") val makeId: String,
    @SerializedName("name") val modelName: String,
    @SerializedName("updated_at") val _updatedDate: Date
)