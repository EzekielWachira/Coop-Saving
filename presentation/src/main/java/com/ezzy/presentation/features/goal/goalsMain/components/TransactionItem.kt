package com.ezzy.presentation.features.goal.goalsMain.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ezzy.domain.models.Contribution

@Composable
fun TransactionItem(
    contribution: Contribution
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Deposit",
                fontWeight = FontWeight.Medium
            )
            Text(
                text = contribution.date.toString(),
                color = Color.Gray,
                fontSize = 12.sp
            )
        }

        Text(
            text = "KES ${"%,.2f".format(contribution.amount)}",
            color = Color(0xFF8BC34A),
            fontWeight = FontWeight.Bold
        )
    }
}
