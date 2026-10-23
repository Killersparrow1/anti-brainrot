package com.brainrot.android.feature_stats.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StatsRepositoryImpl @Inject constructor(
    private val dao: ReelsDao
) : StatsRepository {

    private val DATE_FORMAT = DateTimeFormatter.ISO_DATE

    override val todayStats: Flow<DailyStats> = flow {
        val today = LocalDate.now().format(DATE_FORMAT)
        emit(loadDailyStats(today))
    }.flowOn(Dispatchers.IO)

    override val weeklyStats: Flow<WeeklyStats> = flow {
        val today = LocalDate.now()
        val weekStart = today.minusDays(6)
        emit(loadWeeklyStats(weekStart.format(DATE_FORMAT), today.format(DATE_FORMAT)))
    }.flowOn(Dispatchers.IO)

    override val allTimeStats: Flow<AllTimeStats> = flow {
        emit(loadAllTimeStats())
    }.flowOn(Dispatchers.IO)

    override suspend fun getHourlyBreakdown(date: String): List<HourlyStat> = withContext(Dispatchers.IO) {
        // Load hourly data from events table
        emptyList()
    }

    override suspend fun getTopApps(limit: Int): List<AppStat> = withContext(Dispatchers.IO) {
        val today = LocalDate.now().format(DATE_FORMAT)
        dao.getAppBreakdown(today)
            .take(limit)
            .map { AppStat(
                packageName = it.appPackage,
                appName = getAppName(it.appPackage),
                count = it.total,
                percentage = 0.0 // Calculate from total
            ) }
    }

    override suspend fun getStreakDays(): Int = withContext(Dispatchers.IO) {
        // Calculate streak from database
        0
    }

    override suspend fun getPersonalityType(): ScrollPersonality = withContext(Dispatchers.IO) {
        // Analyze patterns
        ScrollPersonality.STEADY
    }

    private fun loadDailyStats(date: String): DailyStats {
        val total = dao.getTodayTotal(date) ?: 0
        val breakdown = dao.getAppBreakdown(date).toMap()
        return DailyStats(
            date = date,
            totalReels = total,
            totalTimeMinutes = total * 15, // Estimate 15s per reel
            byApp = breakdown,
            byHour = emptyMap()
        )
    }

    private fun loadWeeklyStats(start: String, end: String): WeeklyStats {
        val daily = dao.getLast7Days().map { entity ->
            DailyStats(
                date = entity.date,
                totalReels = entity.totalCount,
                totalTimeMinutes = entity.totalCount * 15,
                byApp = entity.appBreakdown?.let { parseBreakdown(it) } ?: emptyMap(),
                byHour = emptyMap()
            )
        }
        val total = daily.sumOf { it.totalReels }
        val avg = if (daily.isNotEmpty()) total.toDouble() / daily.size else 0.0
        val topApps = daily.flatMap { it.byApp }
            .groupBy { it.key }
            .mapValues { it.value.sumOf { it.value } }
            .toList()
            .sortedByDescending { it.second }
            .take(5)
            .map { AppStat(
                packageName = it.first,
                appName = getAppName(it.first),
                count = it.second,
                percentage = if (total > 0) it.second * 100.0 / total else 0.0
            ) }

        return WeeklyStats(
            weekStart = start,
            weekEnd = end,
            totalReels = total,
            dailyAverage = avg,
            dailyBreakdown = daily,
            topApps = topApps
        )
    }

    private fun loadAllTimeStats(): AllTimeStats {
        // Aggregate all-time stats
        return AllTimeStats(
            totalReels = 0,
            totalDays = 0,
            averagePerDay = 0.0,
            bestDay = null,
            worstDay = null,
            currentStreak = 0,
            longestStreak = 0
        )
    }

    private fun parseBreakdown(json: String): Map<String, Int> {
        // Parse JSON
        return emptyMap()
    }

    private fun getAppName(packageName: String): String = when (packageName) {
        "com.instagram.android", "com.instagram.lite" -> "Instagram"
        "com.google.android.youtube" -> "YouTube"
        "com.zhiliaoapp.musically", "com.ss.android.ugc.trill" -> "TikTok"
        "com.snapchat.android" -> "Snapchat"
        "com.facebook.katana", "com.facebook.lite" -> "Facebook"
        else -> packageName
    }
}