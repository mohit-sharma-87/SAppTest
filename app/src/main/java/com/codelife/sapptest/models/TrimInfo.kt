package com.codelife.sapptest.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class TrimInfo(

    @ColumnInfo
    @SerializedName("active") val active: Boolean,

    @PrimaryKey
    @ColumnInfo
    @SerializedName("id") val trimId: String,

    @ColumnInfo
    @SerializedName("make_id") val makeId: String,

    @ColumnInfo
    @SerializedName("name") val trimName: String,

    @ColumnInfo
    @SerializedName("updated_at") val _updatedDate: Date,

    @ColumnInfo
    @SerializedName("model_ids") val modelMapping: List<String>
)