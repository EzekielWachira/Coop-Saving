package com.ezzy.presentation.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ezzy.presentation.features.navigation.graphs.AppNavGraph

@Composable
fun RootNav() {
    val navController = rememberNavController()

    AppNavGraph(navController)
}