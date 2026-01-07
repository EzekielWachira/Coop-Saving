package com.ezzy.data.repository

import com.ezzy.data.room.dao.ContributionDao
import com.ezzy.data.room.dao.GoalDao
import com.ezzy.data.room.mappers.toDomain
import com.ezzy.data.room.mappers.toEntity
import com.ezzy.domain.models.Goal
import com.ezzy.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GoalRepositoryImpl(
    private val goalDao: GoalDao,
    private val contributionDao: ContributionDao
) : GoalRepository {

    override fun getGoals(): Flow<List<Goal>> =
        goalDao.getAllGoals().map { entities ->
            entities.map { entity ->
                val totalSaved =
                    contributionDao.getTotalSavedForGoal(entity.id) ?: 0.0

                entity.toDomain(totalSaved)
            }
        }

    override suspend fun getGoalById(goalId: Long): Goal? {
        val goalEntity = goalDao.getGoalById(goalId) ?: return null
        val totalSaved = contributionDao.getTotalSavedForGoal(goalId) ?: 0.0
        return goalEntity.toDomain(totalSaved)
    }

    override suspend fun addGoal(goal: Goal): Long {
        val entity = goal.toEntity()
        return goalDao.insertGoal(entity)
    }

    override suspend fun deleteGoal(goal: Goal) {
        goalDao.deleteGoal(goal.toEntity())
    }
}