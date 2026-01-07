package com.ezzy.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ezzy.data.room.entities.ContributionEntity
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
        SELECT SUM(amount) FROM contributions 
        WHERE goalId = :goalId
    """)
    suspend fun getTotalSavedForGoal(goalId: Long): Double?
}