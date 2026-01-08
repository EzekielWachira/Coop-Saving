package com.ezzy.presentation.features.goal.goalsMain.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ezzy.designsystem.theme.DarkGreen
import com.ezzy.designsystem.theme.DarkGrey
import com.ezzy.designsystem.theme.TextBlue
import com.ezzy.designsystem.theme.TextBlueDark
import com.ezzy.designsystem.theme.TextColor
import com.ezzy.designsystem.theme.TextGreen
import com.ezzy.domain.enums.GoalCategory
import com.ezzy.domain.models.Goal

@Composable
fun GoalCard(
    goal: Goal,
    onDeposit: (Long) -> Unit,
    onWithdraw: (Long) -> Unit
) {
    val progress = goal.progressPercent / 100f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = when (goal.category) {
                        GoalCategory.KIDS ->
                            listOf(TextBlue, TextBlueDark)

                        GoalCategory.TRAVELLING ->
                            listOf(TextGreen, DarkGreen)

                        else ->
                            listOf(DarkGrey, TextColor)
                    }
                )
            )
            .padding(16.dp)
    ) {
        Column {
            Text(goal.name, color = Color.White)

            Spacer(Modifier.height(8.dp))

            Text(
                text = "KES ${"%,.2f".format(goal.totalSaved)}",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(8.dp)),
                color = TextGreen,
                trackColor = Color.White.copy(alpha = 0.3f)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Target Amount (KES) ${"%,.2f".format(goal.targetAmount)}",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 13.sp
            )

            Spacer(Modifier.weight(1f))

            Row {
                ActionButton(
                    text = "Deposit",
                    filled = true,
                    modifier = Modifier.weight(1f),
                    onClick = { onDeposit(goal.id) }
                )
                Spacer(Modifier.width(12.dp))
                ActionButton(
                    text = "Withdraw",
                    filled = false,
                    modifier = Modifier.weight(1f),
                    onClick = { onWithdraw(goal.id) }
                )
            }
        }
    }
}


@Composable
private fun ActionButton(
    text: String,
    filled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val background =
        if (filled) TextGreen else Color.Transparent

    val border =
        if (!filled) BorderStroke(1.dp, Color.White) else null

    Box(
        modifier = modifier
            .height(42.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(background)
            .then(
                if (border != null) Modifier.border(
                    border,
                    RoundedCornerShape(8.dp)
                ) else Modifier
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White)
    }
}

