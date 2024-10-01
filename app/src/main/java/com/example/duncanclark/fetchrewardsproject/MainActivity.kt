package com.example.duncanclark.fetchrewardsproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.content.res.ResourcesCompat.ThemeCompat
import com.example.duncanclark.fetchrewardsproject.ui.composable.MainActivityNavHost
import com.example.duncanclark.fetchrewardsproject.ui.theme.FetchRewardsProjectTheme
import com.example.duncanclark.ui_feature_listitems.composable.screen.ListItemsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchRewardsProjectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(
                                stringResource(R.string.app_name)
                            )}
                        )
                    }
                ) { innerPadding ->
                    MainActivityNavHost(Modifier.padding(innerPadding))
                }
            }
        }
    }
}