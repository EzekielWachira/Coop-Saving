package com.ezzy.presentation.features.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ezzy.presentation.features.goal.createGoal.CreateGoalRootScreen
import com.ezzy.presentation.features.goal.createGoal.viewmodel.CreateGoalViewModel
import com.ezzy.presentation.features.home.HomeRootScreen
import com.ezzy.presentation.features.home.enums.NavDirection
import com.ezzy.presentation.features.navigation.AppRoute

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Home
    ) {

        composable<AppRoute.Home> {
            HomeRootScreen(
                navigate = { direction ->
                    when (direction) {
                        NavDirection.GoalSavings -> {
                            navController.navigate(AppRoute.CreateGoalsScreen)
                        }
                        NavDirection.LearnSavings -> {}
                        NavDirection.InvestmentStyle -> {}
                    }
                }
            )
        }

        composable<AppRoute.CreateGoalsScreen> {backStackEntry ->
            val viewModel: CreateGoalViewModel = hiltViewModel(backStackEntry)
            CreateGoalRootScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToMyGoals = {},
                viewModel = viewModel
            )
        }

    }
}
