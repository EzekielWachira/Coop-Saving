package com.ezzy.presentation.features.goal.withdraw.state

import androidx.compose.runtime.Stable
import com.ezzy.domain.models.Goal
import com.ezzy.presentation.features.goal.withdraw.enums.WithdrawDestination
import com.ezzy.presentation.features.goal.withdraw.models.CreditAccount
import com.ezzy.presentation.mviSetUp.MviState

@Stable
data class WithdrawState(
    val goals: List<Goal> = emptyList(),
    val selectedGoalId: Long? = null,

    val withdrawTo: WithdrawDestination = WithdrawDestination.MPESA,
    val phoneNumber: String = "",

    val amount: String = "",

    val isProcessing: Boolean = false,
    val showSuccessDialog: Boolean = false,
    val withdrawnAmount: Double? = null,
    val creditAccount: CreditAccount? = CreditAccount(
        name = "Salary Account",
        accountNumber = "011090145246202",
        balance = 87_000.0
    )
) : MviState {

    val selectedGoal: Goal?
        get() = goals.firstOrNull { it.id == selectedGoalId }

    val availableBalance: Double
        get() = selectedGoal?.totalSaved ?: 0.0
}
