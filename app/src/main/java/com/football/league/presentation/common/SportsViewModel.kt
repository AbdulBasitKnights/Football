package com.football.league.presentation.common

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.football.league.data.datasource.local.AppDatabase
import com.football.league.data.datasource.remote.dto.Club
import com.football.league.data.datasource.remote.dto.League
import com.football.league.data.datasource.remote.dto.Response
import com.football.league.presentation.util.db
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val database: AppDatabase,
    val application: Application
) : ViewModel() {
    var onDataSaved: (() -> Unit)? = null
    fun fetchDBLeague(){
        viewModelScope.launch {
            db=database.leagueDao().getAllLeagues()
            Log.e("fetchedDB","$db")
        }
    }
    fun addLeaguesToDatabase() {
        viewModelScope.launch {
            val jsonString = getJsonDataFromAsset(application.applicationContext, "leagueData.json")
            val leagues = parseJsonToLeagues(jsonString)
            insertLeaguesIntoDatabase(leagues)
        }
    }

    private suspend fun getJsonDataFromAsset(context: Context, fileName: String): String {
        return withContext(Dispatchers.IO) {
            context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        }
    }

    private fun parseJsonToLeagues(jsonString: String): List<League> {
        val gson = Gson()
        val type = object : TypeToken<Response>() {}.type
        val leaguesResponse: Response = gson.fromJson(jsonString, type)
        return leaguesResponse.leagues
    }

    private suspend fun insertLeaguesIntoDatabase(leagues: List<League>) {
        withContext(Dispatchers.IO) {
            database.leagueDao().insertAll(leagues)
        }
        // Notify UI after data is saved
        onDataSaved?.invoke()
    }
    fun retrieveClubs(leagueName: String, onClubsRetrieved: (List<Club>) -> Unit) {
        viewModelScope.launch {
            val json = fetchClubsJson(leagueName)
            val clubs = json?.let { parseClubsJson(it) } ?: emptyList()
            onClubsRetrieved(clubs)
        }
    }

    fun saveClubsToDatabase(clubs: List<Club>) {
        viewModelScope.launch {
            database.leagueDao().insertAllClubs(clubs)
        }
    }
    suspend fun fetchClubsJson(league: String): String? {
        withContext(Dispatchers.IO){
            val url = URL("https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=$league")
            val connection = url.openConnection() as HttpURLConnection

            return@withContext try {
                connection.inputStream.bufferedReader().use { it.readText() }
            } finally {
                connection.disconnect()
            }
        }
      return null
    }
    fun parseClubsJson(json: String): List<Club> {
        val clubs = mutableListOf<Club>()
        val jsonObj = JSONObject(json)
        val teamsArray = jsonObj.getJSONArray("teams")

        for (i in 0 until teamsArray.length()) {
            val teamObj = teamsArray.getJSONObject(i)
            val club = Club(
                idTeam = teamObj.getString("idTeam"),
                name = teamObj.getString("strTeam"),
                strTeamShort = teamObj.optString("strTeamShort"),
                strAlternate = teamObj.optString("strAlternate"),
                intFormedYear = teamObj.optInt("intFormedYear"),
                strLeague = teamObj.optString("strLeague"),
                idLeague = teamObj.optString("idLeague"),
                strStadium = teamObj.optString("strStadium"),
                strKeywords = teamObj.optString("strKeywords"),
                strStadiumThumb = teamObj.optString("strStadiumThumb"),
                strStadiumLocation = teamObj.optString("strStadiumLocation"),
                intStadiumCapacity = teamObj.optInt("intStadiumCapacity"),
                strWebsite = teamObj.optString("strWebsite"),
                strTeamJersey = teamObj.optString("strTeamJersey"),
                strTeamLogo = teamObj.optString("strTeamLogo")
            )
            clubs.add(club)
        }

        return clubs
    }
}
