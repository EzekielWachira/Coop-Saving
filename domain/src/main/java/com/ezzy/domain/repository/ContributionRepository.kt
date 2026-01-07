package com.ezzy.domain.repository

import com.ezzy.domain.models.Contribution
import kotlinx.coroutines.flow.Flow

interface ContributionRepository {

    fun getContributionsForGoal(goalId: Long): Flow<List<Contribution>>

    suspend fun addContribution(goalId: Long, amount: Double)

    suspend fun getTotalSaved(goalId: Long): Double
}