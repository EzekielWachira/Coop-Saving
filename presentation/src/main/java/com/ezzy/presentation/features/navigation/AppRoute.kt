package com.ezzy.presentation.features.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {

    @Serializable
    data object Home : AppRoute

    @Serializable
    data class Details(
        val id: String
    ) : AppRoute

    @Serializable
    data object Profile : AppRoute

    @Serializable
    data object CreateGoalsScreen : AppRoute
}