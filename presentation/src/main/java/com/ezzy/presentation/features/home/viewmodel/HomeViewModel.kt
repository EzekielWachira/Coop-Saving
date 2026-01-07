package com.ezzy.presentation.features.home.viewmodel

import com.ezzy.presentation.features.home.actions.HomeAction
import com.ezzy.presentation.features.home.enums.HomeCardAction
import com.ezzy.presentation.features.home.enums.NavDirection
import com.ezzy.presentation.features.home.events.HomeEvent
import com.ezzy.presentation.features.home.state.HomeState
import com.ezzy.presentation.mviSetUp.viewModel.BaseMviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseMviViewModel<HomeState, HomeAction, HomeEvent>(HomeState()) {

    init {
        registerReducers()
    }

    private fun registerReducers() {

    }

    override fun onUnhandledAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnCardClicked -> {
                when (action.action) {
                    HomeCardAction.SAVING -> {
                        sendEvent(HomeEvent.Navigate(NavDirection.GoalSavings))
                    }

                    HomeCardAction.INVESTMENT -> {
                        sendEvent(HomeEvent.Navigate(NavDirection.InvestmentStyle))
                    }
                }
            }

            HomeAction.OnProfileClicked -> {
                sendEvent(HomeEvent.Navigate(NavDirection.LearnSavings))
            }
        }
    }
}