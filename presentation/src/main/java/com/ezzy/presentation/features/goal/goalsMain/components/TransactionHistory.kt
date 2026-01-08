package com.ezzy.presentation.features.goal.goalsMain.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ezzy.domain.models.Contribution

@Composable
fun TransactionHistory(
    contributions: List<Contribution>
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Transaction History",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "View all",
                color = Color(0xFF8BC34A)
            )
        }

        Spacer(Modifier.height(12.dp))

        contributions.forEach { contribution ->
            TransactionItem(contribution)
        }
    }
}
