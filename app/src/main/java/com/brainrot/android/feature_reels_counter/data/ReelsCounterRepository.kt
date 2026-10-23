package com.brainrot.android.feature_reels_counter.data

import com.brainrot.android.feature_stats.data.ReelsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface ReelsCounterRepository {
    val currentCount: MutableStateFlow<Int>
    val todayTotal: Flow<Int>
    val weeklyData: Flow<List<DailyReelCount>>

    suspend fun incrementCount(appPackage: String)
    suspend fun getCountForApp(appPackage: String): Int
    suspend fun resetDailyCount()
    suspend fun setDailyLimit(limit: Int)
    fun getDailyLimit(): Int
}

data class DailyReelCount(
    val date: String, // YYYY-MM-DD
    val total: Int,
    val byApp: Map<String, Int>
)

data class ReelEvent(
    val timestamp: Long,
    val appPackage: String,
    val reelId: String? = null,
    val durationMs: Long = 0
)