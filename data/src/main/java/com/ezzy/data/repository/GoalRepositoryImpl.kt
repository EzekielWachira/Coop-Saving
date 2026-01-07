package com.ezzy.data.repository

import com.ezzy.data.room.dao.ContributionDao
import com.ezzy.data.room.dao.GoalDao
import com.ezzy.data.room.mappers.toDomain
import com.ezzy.data.room.mappers.toEntity
import com.ezzy.domain.enums.GoalStatus
import com.ezzy.domain.models.Goal
import com.ezzy.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class GoalRepositoryImpl(
    private val goalDao: GoalDao,
    private val contributionDao: ContributionDao
) : GoalRepository {

    override fun getGoals(
        status: GoalStatus
    ): Flow<List<Goal>> =
        combine(
            goalDao.getGoalsByStatus(status),
            contributionDao.getTotalsPerGoal()
        ) { goalEntities, totals ->

            val totalsMap = totals.associateBy(
                keySelector = { it.goalId },
                valueTransform = { it.total }
            )

            goalEntities.map { entity ->
                entity.toDomain(
                    totalSaved = totalsMap[entity.id] ?: 0.0
                )
            }
        }

    override suspend fun getGoalById(goalId: Long): Goal? {
        val entity = goalDao.getGoalById(goalId) ?: return null
        val totalSaved = contributionDao.getTotalSavedForGoal(goalId)
        return entity.toDomain(totalSaved)
    }

    override suspend fun addGoal(goal: Goal): Long =
        goalDao.insertGoal(goal.toEntity())

    override suspend fun deleteGoal(goal: Goal) =
        goalDao.deleteGoal(goal.toEntity())
}