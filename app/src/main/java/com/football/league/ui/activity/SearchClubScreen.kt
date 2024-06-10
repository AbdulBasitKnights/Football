package com.football.league.ui.activity

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.football.league.presentation.common.SportsViewModel

@Composable
fun SearchClubsScreen(viewModel: SportsViewModel= androidx.lifecycle.viewmodel.compose.viewModel()) {
    // Your composable implementation for this screen
    Text("Search Clubs Screen")
}