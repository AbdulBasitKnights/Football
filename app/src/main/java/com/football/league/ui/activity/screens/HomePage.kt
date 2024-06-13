package com.football.league.ui.activity.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import  com.football.league.R
import com.football.league.ui.activity.MainViewModel
import com.football.league.ui.nav.SearchClub
import com.football.league.ui.nav.SearchJersey
import com.football.league.ui.nav.SearchLeague

@Composable
fun HomePage(navigate: (String) -> Unit, viewModel: MainViewModel = hiltViewModel()) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = {
                    viewModel.insertLeagues(context)
                },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("Add Leagues to DB")
            }
            Button(
                onClick = { navigate(SearchLeague) },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("Search for Clubs By League")
            }
            Button(
                onClick = { navigate(SearchClub) },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("Search for Clubs")
            }

            Button(
                onClick = { navigate(SearchJersey) },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("Search for Team Jersey")
            }
        }
    }
}