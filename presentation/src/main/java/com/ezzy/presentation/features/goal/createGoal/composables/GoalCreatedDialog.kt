package com.ezzy.presentation.features.goal.createGoal.composables

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ezzy.designsystem.theme.CoopSavingTheme
import com.ezzy.designsystem.theme.TextGreen

@Composable
fun GoalCreatedDialog(
    goalName: String,
    onDismiss: () -> Unit,
    onGoToGoals: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(shape = RoundedCornerShape(16.dp)) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    tint = TextGreen,
                    modifier = Modifier.size(72.dp),
                    contentDescription = null
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "$goalName Goal",
                    fontWeight = FontWeight.Bold,
                    color = TextGreen
                )

                Text("Created Successfully")

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

@Preview
@Composable
private fun GoalCreatedDialogPreview() {
    CoopSavingTheme {
        GoalCreatedDialog(goalName = "Medicine", onDismiss = {}, onGoToGoals = {})
    }
}