package com.ezzy.presentation.features.goal.createGoal

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ezzy.designsystem.theme.CoopSavingTheme
import com.ezzy.domain.enums.GoalCategory
import com.ezzy.presentation.features.common.DatePickerBottomSheet
import com.ezzy.presentation.features.goal.createGoal.actions.CreateGoalAction
import com.ezzy.presentation.features.goal.createGoal.composables.GoalCategoryDropdown
import com.ezzy.presentation.features.goal.createGoal.composables.GoalCreatedDialog
import com.ezzy.presentation.features.goal.createGoal.events.CreateGoalEvent
import com.ezzy.presentation.features.goal.createGoal.state.CreateGoalState
import com.ezzy.presentation.features.goal.createGoal.viewmodel.CreateGoalViewModel
import com.ezzy.presentation.features.goal.createGoal.viewmodel.NewViewModel
import com.ezzy.presentation.features.utils.appBackground

@Composable
fun CreateGoalRootScreen(
    viewModel: CreateGoalViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToMyGoals: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.events.collect { wrapper ->
            wrapper.consume()?.let { event ->
                when (event) {
                    is CreateGoalEvent.ShowError -> {
                        Toast
                            .makeText(context, event.message, Toast.LENGTH_SHORT)
                            .show()
                    }

                    CreateGoalEvent.NavigateToMyGoals -> {
                        onNavigateToMyGoals()
                    }

                    CreateGoalEvent.NavigateBack -> onNavigateBack()
                }
            }
        }
    }

    CreateGoalScreen(
        state = state,
        dispatchAction = viewModel::dispatch
    )

}

@Composable
private fun CreateGoalScreen(
    state: CreateGoalState = CreateGoalState(),
    dispatchAction: (CreateGoalAction) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .appBackground()
            .imePadding()
    ) {
        CreateGoalContent(
            state = state,
            onBack = {
                dispatchAction(CreateGoalAction.NavigateBack)
            },
            onNameChange = {
                dispatchAction(
                    CreateGoalAction.OnNameChange(it)
                )
            },
            onCategorySelected = {
                dispatchAction(
                    CreateGoalAction.OnCategorySelected(it)
                )
            },
            onTargetAmountChange = {
                dispatchAction(
                    CreateGoalAction.OnTargetAmountChange(it)
                )
            },
            onDateClick = {
                dispatchAction(
                    CreateGoalAction.OnDateClicked
                )
            },
            onSubmit = {
                dispatchAction(CreateGoalAction.Submit)
            }
        )


        when {
            state.showDatePicker -> {
                DatePickerBottomSheet(
                    selectedDate = state.targetDate,
                    onDateSelected = {
                        dispatchAction(
                            CreateGoalAction.OnDateSelected(it)
                        )
                    },
                    onDismiss = {
                        dispatchAction(
                            CreateGoalAction.DismissDatePicker
                        )
                    }
                )
            }
            state.showSuccessDialog -> {
                GoalCreatedDialog(
                    goalName = state.name,
                    onDismiss = {
                        dispatchAction(
                            CreateGoalAction.DismissSuccessDialog
                        )
                    },
                    onGoToGoals = {
                        dispatchAction(
                            CreateGoalAction.GoToMyGoals
                        )
                    }
                )
            }
            state.isSaving -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.25f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun CreateGoalContent(
    state: CreateGoalState,
    onBack: () -> Unit,
    onNameChange: (String) -> Unit,
    onCategorySelected: (GoalCategory) -> Unit,
    onTargetAmountChange: (String) -> Unit,
    onDateClick: () -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 40.dp,
                bottom = 16.dp
            )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }

            Spacer(Modifier.width(8.dp))

            Text(
                text = "Create a Goal",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.name,
            onValueChange = onNameChange,
            label = { Text("Goal Name") }
        )

        Spacer(Modifier.height(16.dp))

        GoalCategoryDropdown(
            selected = state.category,
            onSelected = onCategorySelected
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.targetAmount,
            onValueChange = onTargetAmountChange,
            label = { Text("Target Amount") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            leadingIcon = {
                Text(
                    text = "KES",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.targetDate?.toString() ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Target Date") },
            trailingIcon = {
                IconButton(onClick = onDateClick) {
                    Icon(Icons.Default.DateRange, null)
                }
            }
        )

        Spacer(Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isSaving,
            onClick = onSubmit
        ) {
            Text("Create Goal")
        }
    }
}

@Preview
@Composable
private fun CreateGoalScreenPreview() {
    CoopSavingTheme {
        CreateGoalScreen()
    }
}
