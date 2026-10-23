package com.brainrot.android.feature_stats.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.List

@Dao
interface ReelsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReelEvent(event: ReelEventEntity)

    @Query("SELECT * FROM reel_events WHERE date = :date AND app_package = :appPackage")
    suspend fun getEvent(date: String, appPackage: String): ReelEventEntity?

    @Query("SELECT SUM(count) FROM reel_events WHERE date = :date")
    suspend fun getTodayTotal(date: String = getCurrentDate()): Int?

    @Query("SELECT * FROM reel_events WHERE date = :date")
    suspend fun getEventsForDate(date: String): List<ReelEventEntity>

    @Query("SELECT app_package, SUM(count) as total FROM reel_events WHERE date = :date GROUP BY app_package")
    suspend fun getAppBreakdown(date: String): List<AppBreakdown>

    @Query("SELECT date, SUM(count) as total FROM reel_events GROUP BY date ORDER BY date DESC LIMIT 7")
    suspend fun getLast7Days(): List<DailySummary>

    @Query("SELECT count FROM reel_events WHERE date = :date AND app_package = :appPackage")
    suspend fun getCountForApp(date: String, appPackage: String): Int?

    @Query("DELETE FROM reel_events WHERE date = :date")
    suspend fun resetDailyCount(date: String)

    @Transaction
    suspend fun incrementCount(date: String, appPackage: String) {
        val existing = getEvent(date, appPackage)
        if (existing != null) {
            existing.count++
            insertReelEvent(existing)
        } else {
            insertReelEvent(ReelEventEntity(
                date = date,
                appPackage = appPackage,
                count = 1,
                lastUpdated = System.currentTimeMillis()
            ))
        }
    }

    companion object {
        @Query("SELECT date('now')")
        abstract fun getCurrentDate(): String
    }
}

data class AppBreakdown(
    val appPackage: String,
    val total: Int
)

data class DailySummary(
    val date: String,
    val totalCount: Int,
    val appBreakdown: String? = null // JSON
)