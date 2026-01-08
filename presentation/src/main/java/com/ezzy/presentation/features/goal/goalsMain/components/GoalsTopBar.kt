package com.ezzy.presentation.features.goal.goalsMain.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ezzy.designsystem.icons.AppIcons
import com.ezzy.designsystem.icons.User
import com.ezzy.designsystem.theme.CoopSavingTheme
import com.ezzy.designsystem.theme.ForestTeal
import com.ezzy.designsystem.theme.PineDark

@Composable
private fun GoalsTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
//            .height(110.dp)
            .background(
                brush = Brush.linearGradient(
                    listOf(PineDark, ForestTeal)
                )
            )
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 40.dp,
                bottom = 16.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 16.dp, alignment = Alignment.CenterHorizontally
            )
        ) {
            Icon(
                imageVector = AppIcons.User,
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(50.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Hello There!",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "It’s a good day to save", color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun GoalsTopBarPreview() {
    CoopSavingTheme {
        GoalsTopBar()
    }
}