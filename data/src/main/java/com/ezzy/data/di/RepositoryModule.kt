package com.ezzy.data.di

import com.ezzy.data.repository.ContributionRepositoryImpl
import com.ezzy.data.repository.GoalRepositoryImpl
import com.ezzy.domain.repository.ContributionRepository
import com.ezzy.domain.repository.GoalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGoalRepository(
        impl: GoalRepositoryImpl
    ): GoalRepository

    @Binds
    @Singleton
    abstract fun bindContributionRepository(
        impl: ContributionRepositoryImpl
    ): ContributionRepository
}