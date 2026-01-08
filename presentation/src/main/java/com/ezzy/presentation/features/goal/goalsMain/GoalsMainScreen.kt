package com.ezzy.presentation.features.goal.goalsMain

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ezzy.domain.models.Contribution
import com.ezzy.domain.models.Goal
import com.ezzy.presentation.features.goal.goalsMain.action.GoalsAction
import com.ezzy.presentation.features.goal.goalsMain.components.GoalsHeader
import com.ezzy.presentation.features.goal.goalsMain.components.GoalsPager
import com.ezzy.presentation.features.goal.goalsMain.components.GoalsTopBar
import com.ezzy.presentation.features.goal.goalsMain.components.TransactionHistory
import com.ezzy.presentation.features.goal.goalsMain.events.GoalsEvent
import com.ezzy.presentation.features.goal.goalsMain.viewModel.GoalsViewModel
import com.ezzy.presentation.features.utils.appBackground


@Composable
fun GoalsRoute(
    viewModel: GoalsViewModel = hiltViewModel(),
    onNavigateToCreateGoal: () -> Unit,
    onNavigateToDeposit: (Long) -> Unit,
    onNavigateToWithdraw: (Long) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { wrapper ->
            wrapper.consume()?.let {event->
                when (event) {
                    GoalsEvent.NavigateToCreateGoal ->
                        onNavigateToCreateGoal()

                    is GoalsEvent.NavigateToDeposit ->
                        onNavigateToDeposit(
                            event.goalId
                        )

                    is GoalsEvent.NavigateToWithdraw ->
                        onNavigateToWithdraw(
                            event.goalId
                        )
                }
            }
        }
    }



    GoalsScreen(
        goals = state.goals,
        contributions = state.contributions,
        onAddGoal = {
            viewModel.dispatch(GoalsAction.OnAddGoalClicked)
        },
        onDeposit = { goalId ->
            viewModel.dispatch(
                GoalsAction.OnDepositClicked(goalId)
            )
        },
        onWithdraw = { goalId ->
            viewModel.dispatch(
                GoalsAction.OnWithdrawClicked(goalId)
            )
        }
    )
}

@Composable
fun GoalsScreen(
    goals: List<Goal>,
    contributions: List<Contribution>,
    onAddGoal: () -> Unit,
    onDeposit: (goalId: Long) -> Unit,
    onWithdraw: (goalId: Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .appBackground()
    ) {
        GoalsTopBar()

        Spacer(Modifier.height(16.dp))

        GoalsHeader(onAddGoal)

        Spacer(Modifier.height(16.dp))

        GoalsPager(
            goals = goals,
            onDeposit = onDeposit,
            onWithdraw = onWithdraw
        )

        Spacer(Modifier.height(24.dp))

        TransactionHistory(contributions)
    }
}

