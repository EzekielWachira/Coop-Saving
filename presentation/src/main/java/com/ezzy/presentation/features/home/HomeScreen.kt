package com.ezzy.presentation.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ezzy.designsystem.theme.CoopSavingTheme
import com.ezzy.presentation.features.home.actions.HomeAction
import com.ezzy.presentation.features.home.composables.GoalSavingsCard
import com.ezzy.presentation.features.home.composables.HomeCard
import com.ezzy.presentation.features.home.enums.NavDirection
import com.ezzy.presentation.features.home.events.HomeEvent
import com.ezzy.presentation.features.home.state.HomeState
import com.ezzy.presentation.features.home.viewmodel.HomeViewModel
import com.ezzy.presentation.features.utils.appBackground

@Composable
fun HomeRootScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigate: (NavDirection) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { wrapper ->
            wrapper.consume()?.let { event ->
                when (event) {
                    is HomeEvent.Navigate -> navigate(event.direction)
                }
            }
        }
    }

    HomeScreen(
        state = state,
        onAction = viewModel::dispatch
    )
}

@Composable
private fun HomeScreen(
    state: HomeState = HomeState(),
    onAction: (HomeAction) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .appBackground()
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F3D2E))
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.White, CircleShape)
                )

                Spacer(Modifier.width(12.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        4.dp
                    )
                ) {
                    Text(
                        text = state.greeting,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = state.subtitle,
                        color = Color.White.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }


        Spacer(Modifier.height(24.dp))

        Text(
            text = "Start Saving Towards Your Goals",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        GoalSavingsCard(
            title = "Goal Savings",
            subtitle = "Turn your goal into \nsavings",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(32.dp))


        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.homeCards) { card ->
                HomeCard(
                    model = card,
                    onClick = {
                        onAction(
                            HomeAction.OnCardClicked(card.action)
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    CoopSavingTheme {
        HomeScreen()
    }
}