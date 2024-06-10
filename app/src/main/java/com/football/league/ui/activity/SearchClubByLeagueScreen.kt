package com.football.league.ui.activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.football.league.data.datasource.remote.dto.Club
import com.football.league.presentation.common.SportsViewModel
import com.football.league.presentation.util.db

@Composable
fun SearchClubsByLeagueScreen(viewModel: SportsViewModel = viewModel()) {
    var leagueName by remember { mutableStateOf("") }
    var clubs by remember { mutableStateOf(emptyList<Club>()) }
    var attempt by remember { mutableStateOf(0) }
   db?.forEach { leagueName= it.name.toString() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = leagueName,
            onValueChange = { leagueName = it },
            label = { Text("League Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.retrieveClubs(leagueName) { retrievedClubs ->
                clubs = retrievedClubs
            }
        }) {
            Text("Retrieve Clubs")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.saveClubsToDatabase(clubs)
        }) {
            Text("Save Clubs to Database")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(clubs) { club ->
                Column {
                    Text("Name: ${club.name}")
                    Text("Short Name: ${club.strTeamShort}")
                    // Display other details as needed
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}