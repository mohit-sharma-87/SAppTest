package com.codelife.sapptest.repo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codelife.sapptest.models.MakeInfo
import com.codelife.sapptest.models.ModelInfo
import com.codelife.sapptest.models.TrimInfo

@Dao
interface CarInfoDao {

    @Query("SELECT * FROM MakeInfo")
    fun getMakes(): List<MakeInfo>

    @Query("SELECT * FROM ModelInfo WHERE makeId = :makeId")
    fun getModels(makeId: String): List<ModelInfo>

    @Query("SELECT * FROM TrimInfo WHERE makeId = :makeId AND modelMapping IN (:modelId)")
    fun getTrims(makeId: String, modelId: String): List<TrimInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUpdateMake(makes: List<MakeInfo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUpdateModel(makes: List<ModelInfo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUpdateTrim(makes: List<TrimInfo>)
}