package com.example.duncanclark.fetchrewardsproject.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.duncanclark.ui_feature_listitems.composable.screen.ListItemsScreen

@Composable
fun MainActivityNavHost(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "list-items-screen"
    ) {
        composable("list-items-screen") {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                ListItemsScreen(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController
                )
            }
        }
    }
}