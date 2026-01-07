package com.ezzy.presentation.features.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ezzy.designsystem.theme.CoopSavingTheme
import com.ezzy.presentation.features.home.models.HomeCardModel
import com.ezzy.presentation.features.home.models.homeCardsList

@Composable
fun HomeCard(
    model: HomeCardModel,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(130.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Transparent)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = model.backgroundRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = model.title,
                    color = model.textColor,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = model.subtitle,
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Image(
                painter = painterResource(id = model.imageRes),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HomeCardPreview() {
    CoopSavingTheme {
        HomeCard(homeCardsList[0], {})
    }
}