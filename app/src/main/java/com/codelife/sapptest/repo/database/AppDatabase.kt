package com.codelife.sapptest.repo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codelife.sapptest.models.MakeInfo
import com.codelife.sapptest.models.ModelInfo
import com.codelife.sapptest.models.TrimInfo

@Database(
    entities = [MakeInfo::class, ModelInfo::class, TrimInfo::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carInfoDao(): CarInfoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "car_info"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}