package com.football.league.ui.activity

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.football.league.presentation.common.SportsViewModel
import com.football.league.ui.nav.Screen

@Composable
fun MainScreen(viewModel: SportsViewModel= androidx.lifecycle.viewmodel.compose.viewModel(),navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context= LocalContext.current
        viewModel.onDataSaved = {
            Toast.makeText(context, "Data saved in DB", Toast.LENGTH_SHORT).show()
        }
        Button(onClick = {viewModel.addLeaguesToDatabase()}) {
            Text("Add Leagues to DB")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(Screen.SearchClubsByLeagueScreen.route) }) {
            Text("Search for Clubs By League")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(Screen.SearchClubsScreen.route) }) {
            Text("Search for Clubs")
        }
    }
}