package com.football.league.ui.nav

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object SearchClubsByLeagueScreen : Screen("search_clubs_by_league_screen")
    object SearchClubsScreen : Screen("search_clubs_screen")
}