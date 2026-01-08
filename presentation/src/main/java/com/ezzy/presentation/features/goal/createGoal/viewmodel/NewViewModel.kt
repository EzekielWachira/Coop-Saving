package com.ezzy.presentation.features.goal.createGoal.viewmodel

import com.ezzy.data.repository.ContributionRepositoryImpl
import com.ezzy.domain.repository.GoalRepository
import com.ezzy.presentation.features.goal.createGoal.actions.CreateGoalAction
import com.ezzy.presentation.features.goal.createGoal.events.CreateGoalEvent
import com.ezzy.presentation.features.goal.createGoal.state.CreateGoalState
import com.ezzy.presentation.mviSetUp.viewModel.BaseMviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(
    private val goalRepository: GoalRepository
) : BaseMviViewModel<
        CreateGoalState,
        CreateGoalAction,
        CreateGoalEvent
        >(CreateGoalState()) {
}