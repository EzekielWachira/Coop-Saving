package com.ezzy.presentation.features.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {

    @Serializable
    data object Home : AppRoute

    @Serializable
    data object CreateGoalsScreen : AppRoute

    @Serializable
    data object GoalsScreen : AppRoute

    @Serializable
    data class WithdrawScreen(val goalId: Long) : AppRoute

    @Serializable
    data class DepositScreen(val goalId: Long) : AppRoute

}