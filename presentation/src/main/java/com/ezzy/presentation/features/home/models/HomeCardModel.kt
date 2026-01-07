package com.ezzy.presentation.features.home.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.ezzy.designsystem.R
import com.ezzy.designsystem.theme.TextBlue
import com.ezzy.designsystem.theme.TextGreen
import com.ezzy.presentation.features.home.enums.HomeCardAction

data class HomeCardModel(
    val title: String,
    val subtitle: String,
    @DrawableRes val imageRes: Int,
    @DrawableRes val backgroundRes: Int,
    val action: HomeCardAction,
    val textColor: Color
)


val homeCardsList = listOf(
    HomeCardModel(
        title = "Learn about savings",
        subtitle = "Discover the world with our new savings, one step towards your goal",
        imageRes = R.drawable.saving_icon,
        backgroundRes = R.drawable.green_bg,
        action = HomeCardAction.SAVING,
        textColor = TextGreen
    ),
    HomeCardModel(
        title = "Learn about investments",
        subtitle = "Answer a few questions to determine your risk profile and find suitable investments",
        imageRes = R.drawable.saving_icon,
        backgroundRes = R.drawable.blue_bg,
        action = HomeCardAction.INVESTMENT,
        textColor = TextBlue
    )
)