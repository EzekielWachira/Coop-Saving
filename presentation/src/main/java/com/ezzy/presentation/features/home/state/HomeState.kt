package com.ezzy.presentation.features.home.state

import com.ezzy.presentation.features.home.models.HomeCardModel
import com.ezzy.presentation.features.home.models.homeCardsList
import com.ezzy.presentation.mviSetUp.MviState

data class HomeState(
    val isLoading: Boolean = false,
    val greeting: String = "Hello There!",
    val subtitle: String = "It's a good day to save",
    val homeCards: List<HomeCardModel> = homeCardsList
): MviState
