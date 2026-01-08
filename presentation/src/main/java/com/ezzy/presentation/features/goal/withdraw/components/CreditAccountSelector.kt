package com.ezzy.presentation.features.goal.withdraw.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ezzy.designsystem.R
import com.ezzy.designsystem.theme.TextGreen
import com.ezzy.presentation.features.goal.withdraw.models.CreditAccount

@Composable
fun CreditAccountSelector(
    account: CreditAccount,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {

        Text(
            text = "Credit Account",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(8.dp))

        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.creditcard),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = account.name,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = account.accountNumber,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Select account",
                    tint = Color.Gray
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = "Available balance: ${"%,.2f".format(account.balance)} KES",
            fontSize = 12.sp,
            color = TextGreen
        )
    }
}
