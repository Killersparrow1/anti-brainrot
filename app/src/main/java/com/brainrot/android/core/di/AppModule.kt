package com.brainrot.android.core.di

import android.content.Context
import androidx.datastore.preferences.PreferencesDataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.brainrot.android.feature_reels_counter.data.ReelsCounterRepository
import com.brainrot.android.feature_reels_counter.data.ReelsCounterRepositoryImpl
import com.brainrot.android.feature_stats.data.ReelsDatabase
import com.brainrot.android.feature_stats.data.ReelsDao
import com.brainrot.android.feature_stats.data.StatsRepository
import com.brainrot.android.feature_stats.data.StatsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@androidx.hilt.android.qualifiers.ApplicationContext context: Context): ReelsDatabase =
        Room.databaseBuilder(context, ReelsDatabase::class.java, "reels.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideReelsDao(database: ReelsDatabase): ReelsDao = database.reelsDao()

    @Provides
    @Singleton
    fun provideDataStore(@androidx.hilt.android.qualifiers.ApplicationContext context: Context): PreferencesDataStore =
        context.preferencesDataStore("antibrainrot_prefs")

    @Provides
    @Singleton
    fun provideReelsCounterRepository(dao: ReelsDao): ReelsCounterRepository =
        ReelsCounterRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideStatsRepository(dao: ReelsDao): StatsRepository =
        StatsRepositoryImpl(dao)
}