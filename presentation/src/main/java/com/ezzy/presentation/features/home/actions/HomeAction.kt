package com.ezzy.presentation.features.home.actions

import com.ezzy.presentation.features.home.enums.HomeCardAction
import com.ezzy.presentation.mviSetUp.MviAction

sealed interface HomeAction: MviAction {
    data object OnProfileClicked : HomeAction
    data class OnCardClicked(val action: HomeCardAction) : HomeAction
}