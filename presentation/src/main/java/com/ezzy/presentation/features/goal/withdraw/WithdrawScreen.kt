package com.ezzy.presentation.features.goal.withdraw

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ezzy.designsystem.theme.CoopSavingTheme
import com.ezzy.designsystem.theme.TextGreen
import com.ezzy.domain.models.Goal
import com.ezzy.presentation.features.goal.withdraw.actions.WithdrawAction
import com.ezzy.presentation.features.goal.withdraw.components.CreditAccountSelector
import com.ezzy.presentation.features.goal.withdraw.components.WithdrawTopAppBar
import com.ezzy.presentation.features.goal.withdraw.enums.WithdrawDestination
import com.ezzy.presentation.features.goal.withdraw.events.WithdrawEvent
import com.ezzy.presentation.features.goal.withdraw.state.WithdrawState
import com.ezzy.presentation.features.goal.withdraw.viewmodel.WithdrawViewModel
import com.ezzy.presentation.features.utils.appBackground

@Composable
fun WithdrawRoute(
    viewModel: WithdrawViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToGoals: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val dispatchAction: (WithdrawAction) -> Unit = viewModel::dispatch

    LaunchedEffect(Unit) {
        viewModel.events.collect { wrapper ->
            wrapper.consume()?.let { event ->
                when (event) {
                    WithdrawEvent.NavigateBack -> onNavigateBack()
                    WithdrawEvent.NavigateToGoals -> onNavigateToGoals()
                    is WithdrawEvent.ShowError ->
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    Box(Modifier.fillMaxSize()) {
        WithdrawScreen(
            state = state,
            onBack = {
                dispatchAction(WithdrawAction.NavigateBack)
            },
            onCancel = {
                dispatchAction(WithdrawAction.NavigateBack)
            },
            onGoalSelected = { id ->
                dispatchAction(
                    WithdrawAction.OnGoalSelected(id)
                )
            },
            onDestinationSelected = {
                dispatchAction(
                    WithdrawAction.OnWithdrawDestinationSelected(it)
                )
            },
            onPhoneChange = {
                dispatchAction(
                    WithdrawAction.OnPhoneNumberChange(it)
                )
            },
            onAmountChange = {
                dispatchAction(
                    WithdrawAction.OnAmountChange(it)
                )
            },
            onSubmit = {
                dispatchAction(
                    WithdrawAction.SubmitWithdraw
                )
            }
        )

    }
}


@Composable
fun WithdrawScreen(
    state: WithdrawState = WithdrawState(),
    onBack: () -> Unit = {},
    onGoalSelected: (Long) -> Unit = {},
    onDestinationSelected: (WithdrawDestination) -> Unit = {},
    onPhoneChange: (String) -> Unit = {},
    onAmountChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    Column(
        Modifier
            .appBackground()
            .fillMaxSize()
    ) {
        WithdrawTopAppBar(
            title = "Withdraw",
            onBack = onBack,
            onCancel = {}
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Goal Name")
            GoalDropdown(
                goals = state.goals,
                selectedGoal = state.selectedGoal,
                onSelected = onGoalSelected
            )

            Spacer(Modifier.height(8.dp))

            Text(
                "Available balance: ${"%,.2f".format(state.availableBalance)} KES",
                color = TextGreen
            )

            Spacer(Modifier.height(16.dp))

            Text("Withdraw to:")
            Row {
                RadioOption(
                    label = "Coop Account",
                    selected = state.withdrawTo == WithdrawDestination.COOP_ACCOUNT
                ) {
                    onDestinationSelected(WithdrawDestination.COOP_ACCOUNT)
                }

                Spacer(Modifier.width(16.dp))

                RadioOption(
                    label = "M-PESA",
                    selected = state.withdrawTo == WithdrawDestination.MPESA
                ) {
                    onDestinationSelected(WithdrawDestination.MPESA)
                }
            }

            AnimatedVisibility(state.withdrawTo == WithdrawDestination.COOP_ACCOUNT) {
                Column {
                    state.creditAccount?.let {
                        CreditAccountSelector(account = it)
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }


            AnimatedVisibility(state.withdrawTo == WithdrawDestination.MPESA) {
                Column {
                    OutlinedTextField(
                        value = state.phoneNumber,
                        onValueChange = onPhoneChange,
                        label = { Text("Phone Number") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                }
            }


            OutlinedTextField(
                value = state.amount,
                onValueChange = onAmountChange,
                label = { Text("Amount to withdraw") },
                leadingIcon = { Text("KES") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.weight(1f))

            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isProcessing,
                onClick = onSubmit,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Withdraw",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(Modifier.height(30.dp))
        }
    }
}

@Composable
fun RadioOption(
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(enabled = true) { onClick() }
            .padding(vertical = 8.dp)
            .offset(x = -(10).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = TextGreen,
                unselectedColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = label,
            fontSize = 14.sp,
            color = if (selected)
                TextGreen
            else
                Color.Black
        )
    }
}


@Composable
fun GoalDropdown(
    goals: List<Goal>,
    selectedGoal: Goal?,
    onSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier, contentAlignment = Alignment.BottomEnd) {

        OutlinedTextField(
            value = selectedGoal?.name.orEmpty(),
            onValueChange = {},
            readOnly = true,
            label = { Text("Goal Name") },
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select goal"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            textStyle = MaterialTheme.typography.titleMedium
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(),
        ) {
            goals.forEach { goal ->
                DropdownMenuItem(
                    text = {
                        Column {
                            Text(
                                text = goal.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "KES ${"%,.2f".format(goal.totalSaved)}",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    onClick = {
                        expanded = false
                        onSelected(goal.id)
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    }
}


@Preview
@Composable
private fun WithdrawScreenPreview() {
    CoopSavingTheme {
        WithdrawScreen()
    }
}