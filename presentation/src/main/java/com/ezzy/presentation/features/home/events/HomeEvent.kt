package com.ezzy.presentation.features.home.events

import com.ezzy.presentation.features.home.enums.NavDirection
import com.ezzy.presentation.mviSetUp.MviEvent

sealed interface HomeEvent: MviEvent {
    data class Navigate(val direction: NavDirection) : HomeEvent
}