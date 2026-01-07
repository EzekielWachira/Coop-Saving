package com.ezzy.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ezzy.data.room.entities.GoalEntity
import com.ezzy.domain.enums.GoalStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: GoalEntity): Long

    @Update
    suspend fun updateGoal(goal: GoalEntity)

    @Delete
    suspend fun deleteGoal(goal: GoalEntity)

    @Query("""
        SELECT * FROM goals 
        WHERE status = :status
        ORDER BY createdAt DESC
    """)
    fun getGoalsByStatus(
        status: GoalStatus = GoalStatus.ACTIVE
    ): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goals WHERE id = :goalId")
    suspend fun getGoalById(goalId: Long): GoalEntity?
}