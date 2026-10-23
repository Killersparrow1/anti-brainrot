package com.brainrot.android.feature_reels_counter.data

import com.brainrot.android.feature_stats.data.ReelsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReelsCounterRepositoryImpl @Inject constructor(
    private val dao: ReelsDao
) : ReelsCounterRepository {

    private val _currentCount = MutableStateFlow(0)
    override val currentCount = _currentCount

    private val DAILY_LIMIT_KEY = "daily_reel_limit"
    private val DEFAULT_LIMIT = 50

    override val todayTotal: Flow<Int> = dao.getTodayTotal()
        .map { it ?: 0 }

    override val weeklyData: Flow<List<DailyReelCount>> = dao.getLast7Days()
        .map { entities ->
            entities.map { entity ->
                DailyReelCount(
                    date = entity.date,
                    total = entity.totalCount,
                    byApp = entity.appBreakdown?.let { parseBreakdown(it) } ?: emptyMap()
                )
            }
        }

    override suspend fun incrementCount(appPackage: String) {
        withContext(Dispatchers.IO) {
            val today = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
            dao.incrementCount(today, appPackage)
            _currentCount.update { it + 1 }
        }
    }

    override suspend fun getCountForApp(appPackage: String): Int {
        return withContext(Dispatchers.IO) {
            val today = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
            dao.getCountForApp(today, appPackage) ?: 0
        }
    }

    override suspend fun resetDailyCount() {
        withContext(Dispatchers.IO) {
            val today = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
            dao.resetDailyCount(today)
            _currentCount.value = 0
        }
    }

    override suspend fun setDailyLimit(limit: Int) {
        // Store in DataStore/Preferences
    }

    override fun getDailyLimit(): Int {
        // Read from DataStore/Preferences
        return DEFAULT_LIMIT
    }

    private fun parseBreakdown(json: String): Map<String, Int> {
        // Parse JSON breakdown
        return emptyMap()
    }
}