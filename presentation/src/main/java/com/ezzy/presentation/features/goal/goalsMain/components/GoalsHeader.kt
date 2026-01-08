package com.ezzy.presentation.features.goal.goalsMain.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ezzy.designsystem.theme.CoopSavingTheme
import com.ezzy.designsystem.theme.TextGreen

@Composable
fun GoalsHeader(
    onAddGoal: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "My Goals",
            fontSize = 18.sp,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "+ Add a Goal",
            color = TextGreen,
            modifier = Modifier.clickable { onAddGoal() },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Preview
@Composable
private fun GoalsHeaderPreview() {
    CoopSavingTheme {
        GoalsHeader({})
    }
}