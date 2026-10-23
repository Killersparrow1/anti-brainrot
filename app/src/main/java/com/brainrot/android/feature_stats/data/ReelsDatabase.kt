package com.brainrot.android.feature_stats.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ReelEventEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class ReelsDatabase : RoomDatabase() {

    abstract fun reelsDao(): ReelsDao

    companion object {
        @Volatile private var INSTANCE: ReelsDatabase? = null

        fun getDatabase(context: Context): ReelsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReelsDatabase::class.java,
                    "reels.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}