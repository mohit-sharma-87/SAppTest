package com.codelife.sapptest.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class MakeInfo(
    @ColumnInfo
    @SerializedName("active") val active: Boolean,

    @PrimaryKey
    @SerializedName("id") val makeId: String,

    @ColumnInfo
    @SerializedName("name") val makeName: String,

    @ColumnInfo
    @SerializedName("logo_uri") val logoImageUrl: String,

    @ColumnInfo
    @SerializedName("updated_at") val _updatedDate: Date
) {

    val updateDate: String
        get() {
            val pattern = "EEE, d MMM hh:mm"
            return "Last updated time is" + SimpleDateFormat(
                pattern,
                Locale.US
            ).format(_updatedDate)
        }
}