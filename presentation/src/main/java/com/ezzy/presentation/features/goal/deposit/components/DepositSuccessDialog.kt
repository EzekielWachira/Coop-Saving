package com.ezzy.presentation.features.goal.deposit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ezzy.designsystem.R
import com.ezzy.designsystem.theme.TextGreen

@Composable
fun DepositSuccessDialog(
    amount: Double,
    onGoToGoals: () -> Unit
) {
    Dialog(onDismissRequest = {}) {
        Card(
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.success),
                    tint = Color.Unspecified,
                    modifier = Modifier.size(72.dp),
                    contentDescription = null
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    "${"%,.2f".format(amount)} KES",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextGreen
                )

                Text("Deposit Successful")

                Spacer(Modifier.height(24.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onGoToGoals
                ) {
                    Text("Go to My Goals")
                }
            }
        }
    }
}
