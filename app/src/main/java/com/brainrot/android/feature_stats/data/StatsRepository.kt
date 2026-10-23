package com.brainrot.android.feature_stats.data

import kotlinx.coroutines.flow.Flow

interface StatsRepository {
    val todayStats: Flow<DailyStats>
    val weeklyStats: Flow<WeeklyStats>
    val allTimeStats: Flow<AllTimeStats>

    suspend fun getHourlyBreakdown(date: String): List<HourlyStat>
    suspend fun getTopApps(limit: Int): List<AppStat>
    suspend fun getStreakDays(): Int
    suspend fun getPersonalityType(): ScrollPersonality
}

data class DailyStats(
    val date: String,
    val totalReels: Int,
    val totalTimeMinutes: Int,
    val byApp: Map<String, Int>,
    val byHour: Map<Int, Int>
)

data class WeeklyStats(
    val weekStart: String,
    val weekEnd: String,
    val totalReels: Int,
    val dailyAverage: Double,
    val dailyBreakdown: List<DailyStats>,
    val topApps: List<AppStat>
)

data class AllTimeStats(
    val totalReels: Long,
    val totalDays: Int,
    val averagePerDay: Double,
    val bestDay: DailyStats?,
    val worstDay: DailyStats?,
    val currentStreak: Int,
    val longestStreak: Int
)

data class HourlyStat(
    val hour: Int, // 0-23
    val count: Int,
    val timeMinutes: Int
)

data class AppStat(
    val packageName: String,
    val appName: String,
    val count: Int,
    val percentage: Double
)

enum class ScrollPersonality {
    LIGHT_TOUCH,      // Very few reels
    STEADY,           // Consistent throughout day
    SNACKER,          // Short bursts
    FEED_HOPPER,      // Jumps between apps
    DEEP_DIVER,       // Long sessions
    EARLY_BIRD,       // Morning scroller
    NIGHT_OWL,        // Late night scroller
    BOOMERANG         // Opens, closes, reopens
}