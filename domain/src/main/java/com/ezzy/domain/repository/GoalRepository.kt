package com.ezzy.domain.repository

import com.ezzy.domain.models.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {

    fun getGoals(): Flow<List<Goal>>

    suspend fun getGoalById(goalId: Long): Goal?

    suspend fun addGoal(goal: Goal): Long

    suspend fun deleteGoal(goal: Goal)
}