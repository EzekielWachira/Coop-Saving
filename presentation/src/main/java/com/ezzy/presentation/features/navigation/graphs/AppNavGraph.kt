package com.ezzy.presentation.features.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ezzy.presentation.features.goal.deposit.DepositRoute
import com.ezzy.presentation.features.goal.createGoal.CreateGoalRootScreen
import com.ezzy.presentation.features.goal.goalsMain.GoalsRoute
import com.ezzy.presentation.features.goal.withdraw.WithdrawRoute
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
                        NavDirection.MyGoals -> {
                            navController.navigate(AppRoute.GoalsScreen)
                        }
                    }
                }
            )
        }

        composable<AppRoute.CreateGoalsScreen> { backStackEntry ->
            CreateGoalRootScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToMyGoals = {
                    navController.navigate(AppRoute.GoalsScreen)
                },
            )
        }

        composable<AppRoute.GoalsScreen> {
            GoalsRoute(
                onNavigateToCreateGoal = {
                    navController.navigate(AppRoute.CreateGoalsScreen)
                },
                onNavigateToDeposit = { goalId ->
                    navController.navigate(
                        AppRoute.DepositScreen(goalId)
                    )
                },
                onNavigateToWithdraw = { goalId ->
                    navController.navigate(
                        AppRoute.WithdrawScreen(goalId)
                    )
                }
            )
        }

        composable<AppRoute.WithdrawScreen> {
            WithdrawRoute(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToGoals = {
                    navController.navigate(AppRoute.GoalsScreen)
                }
            )
        }

        composable<AppRoute.DepositScreen> {
            DepositRoute(
                onBack = {
                    navController.navigateUp()
                },
                onGoToGoals = {
                    navController.navigate(AppRoute.GoalsScreen)
                }
            )
        }
    }
}
