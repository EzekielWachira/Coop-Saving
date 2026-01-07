package com.ezzy.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ezzy.data.room.entities.ContributionEntity
import com.ezzy.data.room.models.GoalTotal
import kotlinx.coroutines.flow.Flow

@Dao
interface ContributionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContribution(contribution: ContributionEntity)

    @Query("""
        SELECT * FROM contributions
        WHERE goalId = :goalId
        ORDER BY date DESC
    """)
    fun getContributionsForGoal(goalId: Long): Flow<List<ContributionEntity>>

    @Query("""
        SELECT goalId, SUM(amount) as total
        FROM contributions
        GROUP BY goalId
    """)
    fun getTotalsPerGoal(): Flow<List<GoalTotal>>

    @Query("""
        SELECT COALESCE(SUM(amount), 0)
        FROM contributions
        WHERE goalId = :goalId
    """)
    suspend fun getTotalSavedForGoal(
        goalId: Long
    ): Double
}