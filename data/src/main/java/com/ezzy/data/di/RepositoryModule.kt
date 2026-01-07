package com.ezzy.data.di

import com.ezzy.data.repository.ContributionRepositoryImpl
import com.ezzy.data.repository.GoalRepositoryImpl
import com.ezzy.domain.repository.ContributionRepository
import com.ezzy.domain.repository.GoalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@[Module InstallIn(ViewModelComponent::class)]
internal abstract class RepositoryModule {

    @[Binds ViewModelScoped]
    abstract fun bindGoalRepository(
        impl: GoalRepositoryImpl
    ): GoalRepository

    @[Binds ViewModelScoped]
    abstract fun bindContributionRepository(
        impl: ContributionRepositoryImpl
    ): ContributionRepository
}