package com.ezzy.presentation.features.goal.createGoal.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ezzy.designsystem.R
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
                    painter = painterResource(R.drawable.success),
                    tint = Color.Unspecified,
                    modifier = Modifier.size(100.dp),
                    contentDescription = null
                )

                Text(
                    text = "$goalName Goal",
                    color = TextGreen,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp
                    )
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Created Successfully",
                    fontSize = 16.sp
                )

                Spacer(Modifier.height(16.dp))
                Text(
                    text = "You are one step closer to reaching your target",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(24.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onGoToGoals,
                    shape = RoundedCornerShape(8.dp)
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