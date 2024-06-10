package com.football.league.ui.activity

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.football.league.presentation.common.SportsViewModel
import kotlinx.coroutines.launch

@Composable
fun AddLeaguesScreen(viewModel: SportsViewModel, onLeaguesAdded: () -> Unit) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        // Fetch leagues data and insert into the database
        scope.launch {
            viewModel.addLeaguesToDatabase()
            onLeaguesAdded()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Leagues are being added to the database...")
    }
}