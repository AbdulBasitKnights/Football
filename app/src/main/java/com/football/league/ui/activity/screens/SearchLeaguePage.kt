package com.football.league.ui.activity.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.football.league.ui.activity.MainViewModel

@Composable
fun SearchLeagueScreen(mainViewModel: MainViewModel = hiltViewModel()) {
    val leaguesState by mainViewModel.countryLeagueResponse.observeAsState()
    val errorState by mainViewModel.error.observeAsState()

    val context = LocalContext.current


    Column(modifier = Modifier.fillMaxSize()) {
        var leagueName by remember { mutableStateOf("") }

        OutlinedTextField(
            value = leagueName,
            onValueChange = { leagueName = it },
            label = { Text("Enter League Name") },
            placeholder = { Text(text = "German Bundesliga")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(
            onClick = { mainViewModel.getAllTeamsInLeague(leagueName) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Retrieve Clubs")
        }

        Button(
            onClick = {
                if (leaguesState != null) {
                    mainViewModel.saveTeamsToDatabase(leaguesState!!, context = context)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = !leaguesState?.teams.isNullOrEmpty(),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Gray
            )
        ) {
            Text("Save clubs to Database")
        }

        if(leaguesState?.teams != null) {
            leaguesState?.let { league ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(league.teams) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
//                                Image(
//                                    painter = rememberAsyncImagePainter(model = item.stadiumThumb),
//                                    contentDescription = "team logo",
//                                    modifier = Modifier.size(180.dp)
//                                )

                                Image(
                                    painter = rememberAsyncImagePainter(model = item.strKit),
                                    contentDescription = "team logo",
                                    modifier = Modifier.size(150.dp)
                                )
                                Text(text = "Team ID: ${item.idTeam}", modifier = Modifier.padding(8.dp))
                                Text(text = "Team name: ${item.name}", modifier = Modifier.padding(8.dp))
                                Text(
                                    text = "Team short name: ${item.teamShort}",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = "Team full name: ${item.alternateName}",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = "Formed Year: ${item.formedYear}",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(text = "League: ${item.league}", modifier = Modifier.padding(8.dp))
                                Text(
                                    text = "League Id: ${item.idLeague}",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = "Stadium name: ${item.stadium}",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = "Keywords: ${item.keywords}",
                                    modifier = Modifier.padding(8.dp)
                                )

                                Text(
                                    text = "Stadium Location: ${item.strCountry}",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = "Stadium Capacity: ${item.stadiumCapacity}",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = "Website: ${item.website}",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Image(
                                    painter = rememberAsyncImagePainter(model = item.strBadge),
                                    contentDescription = "team logo",
                                    modifier = Modifier.size(100.dp)

                                )
                            }
                        }
                    }
                }
            }
        }

                errorState?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }


