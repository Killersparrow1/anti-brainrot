package com.brainrot.android.feature_stats.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reel_events", indices = [
    androidx.room.Index(value = ["date", "app_package"], unique = true)
])
data class ReelEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String, // YYYY-MM-DD
    val appPackage: String,
    val count: Int,
    val lastUpdated: Long,
    val reelIds: String? = null, // JSON array of reel IDs
    val totalDurationMs: Long = 0
)