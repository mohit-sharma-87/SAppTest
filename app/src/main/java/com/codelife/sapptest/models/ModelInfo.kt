package com.codelife.sapptest.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class ModelInfo(
    @ColumnInfo
    @SerializedName("active") val active: Boolean,

    @PrimaryKey
    @ColumnInfo
    @SerializedName("id") val modelId: String,

    @ColumnInfo
    @SerializedName("make_id") val makeId: String,

    @ColumnInfo
    @SerializedName("name") val modelName: String,

    @ColumnInfo
    @SerializedName("updated_at") val _updatedDate: Date
)