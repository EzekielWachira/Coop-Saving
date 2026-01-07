package com.ezzy.data.repository

import com.ezzy.data.room.dao.ContributionDao
import com.ezzy.data.room.database.AppDatabase
import com.ezzy.data.room.entities.ContributionEntity
import com.ezzy.data.room.mappers.toDomain
import com.ezzy.domain.models.Contribution
import com.ezzy.domain.repository.ContributionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContributionRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase
) : ContributionRepository {
    private val contributionDao: ContributionDao = appDatabase.contributionDao()

    override fun getContributionsForGoal(goalId: Long): Flow<List<Contribution>> =
        contributionDao
            .getContributionsForGoal(goalId)
            .map { entities ->
                entities.map { it.toDomain() }
            }

    override suspend fun addContribution(goalId: Long, amount: Double) {
        val entity = ContributionEntity(
            goalId = goalId,
            amount = amount,
            date = System.currentTimeMillis()
        )
        contributionDao.insertContribution(entity)
    }

    override suspend fun getTotalSaved(goalId: Long): Double =
        contributionDao.getTotalSavedForGoal(goalId) ?: 0.0
}