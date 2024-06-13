package com.football.league.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.football.league.ui.activity.screens.HomePage
import com.football.league.ui.activity.screens.SearchClubPage
import com.football.league.ui.activity.screens.SearchJerseyPage
import com.football.league.ui.activity.screens.SearchLeagueScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Home) {

        composable(route = Home) {
            HomePage(navigate = { route -> navHostController.navigate(route) })
        }

        composable(route = SearchLeague) {
            SearchLeagueScreen()
        }

        composable(route = SearchClub){
            SearchClubPage()
        }

        composable(route = SearchJersey){
            SearchJerseyPage()
        }
    }
}