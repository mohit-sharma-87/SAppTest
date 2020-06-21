package com.codelife.sapptest.dao

import com.google.gson.annotations.SerializedName
import java.util.*

data class TrimInfo(
    @SerializedName("active") val active: Boolean,
    @SerializedName("id") val trimId: String,
    @SerializedName("make_id") val makeId: String,
    @SerializedName("name") val trimName: String,
    @SerializedName("updated_at") val _updatedDate: Date,
    @SerializedName("model_ids") val modelMapping: List<String>
)