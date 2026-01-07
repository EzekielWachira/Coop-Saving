package com.ezzy.presentation.features.utils

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.appBackground() =
    Modifier.background(MaterialTheme.colorScheme.background)