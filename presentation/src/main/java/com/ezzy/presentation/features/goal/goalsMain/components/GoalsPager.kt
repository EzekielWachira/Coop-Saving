package com.ezzy.presentation.features.goal.goalsMain.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ezzy.designsystem.theme.TextGreen
import com.ezzy.domain.models.Goal

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoalsPager(
    goals: List<Goal>,
    onDeposit: (Long) -> Unit,
    onWithdraw: (Long) -> Unit
) {
    val pagerState = rememberPagerState { goals.size }

    Column {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 12.dp
        ) { page ->
            GoalCard(
                goal = goals[page],
                onDeposit = onDeposit,
                onWithdraw = onWithdraw
            )
        }

        Spacer(Modifier.height(12.dp))

        PagerIndicator(
            total = goals.size,
            selected = pagerState.currentPage
        )
    }
}


@Composable
private fun PagerIndicator(total: Int, selected: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(total) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == selected)
                            TextGreen
                        else
                            Color.LightGray
                    )
            )
        }
    }
}

