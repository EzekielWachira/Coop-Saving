package com.ezzy.presentation.features.goal.deposit.state

import androidx.compose.runtime.Stable
import com.ezzy.domain.models.Goal
import com.ezzy.presentation.features.goal.deposit.enums.FundSource
import com.ezzy.presentation.features.goal.withdraw.models.CreditAccount
import com.ezzy.presentation.mviSetUp.MviState

@Stable
data class DepositState(
    val goals: List<Goal> = emptyList(),
    val selectedGoalId: Long? = null,

    val fundFrom: FundSource = FundSource.COOP_ACCOUNT,

    val amount: String = "",

    val isSubmitting: Boolean = false,
    val showSuccessDialog: Boolean = false,
    val depositedAmount: Double? = null,
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