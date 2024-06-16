package com.football.league.ui.activity.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.football.league.data.datasource.remote.dto.TeamData
import com.football.league.data.datasource.remote.dto.TeamDetailsResponse
import com.football.league.ui.activity.MainViewModel

@Composable
fun SearchJerseyPage(viewModel: MainViewModel = hiltViewModel()) {

    val leaguesState by viewModel.teams.observeAsState()
    val errorState by viewModel.error.observeAsState()

    val equipmentResults by viewModel.equipmentResult.observeAsState()

    val isLoading = leaguesState == null && errorState == null


    Column(modifier = Modifier.fillMaxSize()) {
        var leagueName by remember { mutableStateOf("") }
        var teamName by remember { mutableStateOf("") }

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
            onClick = { viewModel.getAllTeamsInLeague(leagueName) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Done")
        }

        if (!isLoading) {
            OutlinedTextField(
                value = teamName,
                onValueChange = { teamName = it },
                label = { Text("Enter Team Name") },
                placeholder = { Text(text = "B")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            leaguesState?.let { leagues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    if (teamName.isNotBlank()) {
                        items(leagues.filter { it.name.contains(teamName, ignoreCase = true) }) { team ->
                            ExpandableListItem(
                                team = team,
                                equipmentResults = equipmentResults,
                                onClick = {
                                    viewModel.getEquipmentDetails(team.idTeam.toInt())
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableListItem(
    team: TeamDetailsResponse,
    equipmentResults: TeamData?,
    onClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val expandAnimation: State<Float> = animateFloatAsState(
        targetValue = if (expanded) 540f else 0f,
        animationSpec = tween(1000, easing = FastOutSlowInEasing)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(model = team.strBadge),
                    contentDescription = "Team Badge",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = team.name, modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    expanded = !expanded
                    onClick()
                }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "League: ${team.league}")
                Text(text = "Formed Year: ${team.formedYear}")
                Text(text = "Stadium: ${team.stadium}")
                Text(text = "Country: ${team.strCountry}")
                equipmentResults?.let { equipmentList ->
                    equipmentList.equipment.forEach { equipment ->
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = equipment.equipmentImageUrl),
                                contentDescription = "Jersey",
                                modifier = Modifier.size(150.dp)
                            )
                            Text(text = "Jersey Season: ${equipment.season}")
                        }
                    }
                }
            }
        }
    }
}
/*
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableListItem(
    team: TeamDetailsResponse,
    equipmentResults: TeamData?,
    onClick : () -> Unit
) {
    val expanded = remember { mutableStateOf(false) }

    val expandAnimation: State<Float> = animateFloatAsState(
        targetValue = if (expanded.value) 540f else 0f,
        animationSpec = tween(1000, easing = FastOutSlowInEasing)
    )

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Expandable(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            expanded = expanded.value,
            onExpandChanged = { expanded.value = it },
            title = {
                    Text(text = team.name, modifier = Modifier.weight(8f))
                 },
            content = {
                // Display equipment details (jersey and season)
                equipmentResults?.let { equipmentList ->
                    equipmentList.equipment.forEach { equipment ->
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = equipment.equipmentImageUrl),
                                contentDescription = "Jersey",
                                modifier = Modifier.size(150.dp)
                            )
                            Text(text = "Jersey Season: ${equipment.season}")
                        }
                    }
                }
            },
            expandAnimation = expandAnimation,
            expand = { modifier ->
                IconButton(
                    modifier = modifier,
                    onClick = {
                        expanded.value = !expanded.value
                        onClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }
        )
    }
}*/
