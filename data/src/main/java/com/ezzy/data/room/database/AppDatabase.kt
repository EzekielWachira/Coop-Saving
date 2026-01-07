package com.ezzy.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezzy.data.room.dao.ContributionDao
import com.ezzy.data.room.dao.GoalDao
import com.ezzy.data.room.entities.ContributionEntity
import com.ezzy.data.room.entities.GoalEntity

@Database(
    entities = [
        ContributionEntity::class,
        GoalEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val goalDao: GoalDao
    abstract val contributionDao: ContributionDao
}