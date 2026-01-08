package com.ezzy.presentation.features.goal.deposit

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import com.ezzy.designsystem.theme.CoopSavingTheme
import com.ezzy.presentation.features.goal.deposit.actions.DepositAction
import com.ezzy.presentation.features.goal.deposit.components.DepositSuccessDialog
import com.ezzy.presentation.features.goal.deposit.enums.FundSource
import com.ezzy.presentation.features.goal.deposit.events.DepositEvent
import com.ezzy.presentation.features.goal.deposit.state.DepositState
import com.ezzy.presentation.features.goal.deposit.viewmodel.DepositViewModel
import com.ezzy.presentation.features.goal.withdraw.GoalDropdown
import com.ezzy.presentation.features.goal.withdraw.RadioOption
import com.ezzy.presentation.features.goal.withdraw.components.CreditAccountSelector
import com.ezzy.presentation.features.goal.withdraw.components.WithdrawTopAppBar
import com.ezzy.presentation.features.utils.appBackground

@Composable
fun DepositRoute(
    viewModel: DepositViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onGoToGoals: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val dispatchAction: (DepositAction) -> Unit = viewModel::dispatch

    LaunchedEffect(Unit) {
        viewModel.events.collect { wrapper ->
            wrapper.consume()?.let { event ->
                when (event) {
                    DepositEvent.NavigateBack -> onBack()
                    DepositEvent.NavigateToGoals -> onGoToGoals()
                    is DepositEvent.ShowError ->
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Box(Modifier.fillMaxSize()) {
        DepositScreen(
            state = state,
            onBack = { dispatchAction(DepositAction.NavigateBack) },
            onCancel = { dispatchAction(DepositAction.NavigateBack) },
            onGoalSelected = {
                dispatchAction(
                    DepositAction.GoalSelected(it)
                )
            },
            onFundSourceSelected = {
                dispatchAction(
                    DepositAction.FundSourceSelected(it)
                )
            },
            onAmountChanged = {
                dispatchAction(
                    DepositAction.AmountChanged(it)
                )
            },
            onSubmit = {
                dispatchAction(DepositAction.Submit)
            },
            onPhoneChanged = {
                viewModel.dispatch(DepositAction.PhoneChanged(it))
            },
        )

        if (state.showSuccessDialog) {
            DepositSuccessDialog(
                amount = state.depositedAmount ?: 0.0,
                onGoToGoals = {
                    dispatchAction(
                        DepositAction.GoToGoals
                    )
                }
            )
        }

        if (state.isSubmitting) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
fun DepositScreen(
    state: DepositState = DepositState(),
    onBack: () -> Unit = {},
    onCancel: () -> Unit = {},
    onGoalSelected: (Long) -> Unit = {},
    onFundSourceSelected: (FundSource) -> Unit = {},
    onAmountChanged: (String) -> Unit = {},
    onSubmit: () -> Unit = {},
    onPhoneChanged: (String) -> Unit = {},
) {
    Column(
        Modifier
            .appBackground()
            .fillMaxSize()
    ) {
        WithdrawTopAppBar(
            title = "Deposit",
            onBack = onBack,
            onCancel = onCancel
        )

        Column(
            Modifier
                .weight(1f)
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
                color = Color(0xFF8BC34A)
            )

            Spacer(Modifier.height(16.dp))

            Text("Fund from:")

            Row(
                modifier = Modifier.offset(x = -(10).dp)
            ) {
                RadioOption(
                    label = "Coop Account",
                    selected = state.fundFrom == FundSource.COOP_ACCOUNT
                ) {
                    onFundSourceSelected(FundSource.COOP_ACCOUNT)
                }

                Spacer(Modifier.width(16.dp))

                RadioOption(
                    label = "M-PESA",
                    selected = state.fundFrom == FundSource.MPESA
                ) {
                    onFundSourceSelected(FundSource.MPESA)
                }
            }

            AnimatedVisibility(state.fundFrom == FundSource.COOP_ACCOUNT) {
                Column {
                    state.creditAccount?.let {
                        CreditAccountSelector(account = it)
                    }
                }
            }

            if (state.fundFrom == FundSource.MPESA) {
                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = state.phoneNumber,
                    onValueChange = onPhoneChanged,
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }


            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = state.amount,
                onValueChange = onAmountChanged,
                label = { Text("Amount to deposit") },
                leadingIcon = { Text("KES") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.weight(1f))

            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isSubmitting,
                onClick = onSubmit,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Deposit",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(Modifier.height(30.dp))
        }
    }
}

@Preview
@Composable
private fun DepositScreenPreview() {
    CoopSavingTheme {
        DepositScreen()
    }
}