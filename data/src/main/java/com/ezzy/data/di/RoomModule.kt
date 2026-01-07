package com.ezzy.data.di

import android.content.Context
import androidx.room.Room
import com.ezzy.data.room.dao.ContributionDao
import com.ezzy.data.room.dao.GoalDao
import com.ezzy.data.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context = context,
        AppDatabase::class.java,
        "app_database"
    ).fallbackToDestructiveMigration(false)
        .build()

    @Provides
    fun provideGoalDao(db: AppDatabase): GoalDao =
        db.goalDao

    @Provides
    fun provideContributionDao(db: AppDatabase): ContributionDao =
        db.contributionDao

}