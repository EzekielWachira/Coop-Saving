package com.ezzy.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ezzy.data.room.converters.GoalCategoryConverter
import com.ezzy.data.room.converters.GoalStatusConverter
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
@TypeConverters(
    GoalCategoryConverter::class,
    GoalStatusConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
    abstract fun contributionDao(): ContributionDao
}