package com.football.league.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.football.league.presentation.common.SportsViewModel
import com.football.league.ui.nav.Screen
import com.football.league.ui.theme.QuestionAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: SportsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            QuestionAssignmentTheme{
                MainApp(viewModel)
            }
        }
    }
    @Preview
    @Composable
    fun PreviewSportsScreen() {
        MainApp()
    }

}
@Composable
fun MainApp(viewModel: SportsViewModel = viewModel()) {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(viewModel,navController)
        }
        composable(Screen.SearchClubsByLeagueScreen.route) {
            SearchClubsByLeagueScreen(viewModel)
        }
        composable(Screen.SearchClubsScreen.route) {
            SearchClubsScreen(viewModel)
        }
    }
}