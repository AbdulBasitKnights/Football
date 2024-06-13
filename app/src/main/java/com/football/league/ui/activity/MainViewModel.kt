package com.football.league.ui.activity

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.football.league.R
import com.football.league.data.datasource.remote.dto.CountryLeagues
import com.football.league.data.datasource.remote.dto.EquipmentModel
import com.football.league.data.datasource.remote.dto.FootballLeague
import com.football.league.data.datasource.remote.dto.SearchLeagueEntity
import com.football.league.data.datasource.remote.dto.TeamDetailsResponse
import com.football.league.data.datasource.remote.dto.TeamSearchResponse
import com.football.league.data.datasource.remote.repository.FootballApi
import com.football.league.data.datasource.remote.repository.FootballLeagueRepository
import com.football.league.data.datasource.remote.repository.LeagueApi
import com.football.league.data.datasource.remote.repository.SearchLeagueRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchLeagueRepository: SearchLeagueRepository,
    private val repository: FootballLeagueRepository
): ViewModel() {

    private val _countryLeagueResponse = MutableLiveData<CountryLeagues?>()
    val countryLeagueResponse: MutableLiveData<CountryLeagues?> get() = _countryLeagueResponse

    private val _searchResults = MutableLiveData<List<SearchLeagueEntity>>()
    val searchResults: LiveData<List<SearchLeagueEntity>> get() = _searchResults

    private val _equipmentResults = MutableLiveData<EquipmentModel?>()
    val equipmentResult: LiveData<EquipmentModel?> get() = _equipmentResults

    private val _teamSearchResult = MutableLiveData<TeamSearchResponse>()
    val teamSearchResult : LiveData<TeamSearchResponse> get() = _teamSearchResult

    private val _teams = MutableLiveData<List<TeamDetailsResponse>?>()
    val teams: LiveData<List<TeamDetailsResponse>?> get() = _teams



    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getAllTeamsInLeague(league: String) {
        if (league.isEmpty()) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = LeagueApi.searchAllLeagues(league)
                Log.d("CheckData", "getAllTeamsInLeague: $response")
                // Handle the response
                withContext(Dispatchers.Main) {
                    _countryLeagueResponse.value = response
                    _teams.value = response?.teams
                }
            } catch (e: Exception) {
                // Handle error
                withContext(Dispatchers.Main) {
                    Log.d("CheckData", "getAllTeamsInLeague: $e")
                    _error.value = e.message
                }
            }
        }
    }
    fun saveTeamsToDatabase(teams: CountryLeagues,context: Context) {
        if (teams.teams.isEmpty()) {
            return
        }
        viewModelScope.launch {
          searchLeagueRepository.saveSearchLeagues(teams)
        }
        Toast.makeText(context,"Saved Clubs to database successfully",Toast.LENGTH_SHORT).show()
    }

    fun searchClubsByPartialNameOrLeague(searchQuery: String) {
        if(searchQuery.isEmpty()){
            return
        }
        viewModelScope.launch {
            _searchResults.value = searchLeagueRepository.searchByPartialNameOrLeague(searchQuery)
        }
    }

    fun insertLeagues(context: Context){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val leagues = readFootballLeaguesFromJson(context)
                repository.insertFromJson(leagues)
            }
            Toast.makeText(context,"Leagues Inserted Successfully",Toast.LENGTH_SHORT).show()
        }
    }

    private fun readFootballLeaguesFromJson(context: Context): List<FootballLeague> {
        val inputStream = context.resources.openRawResource(R.raw.leagues)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("leagues")
        val gson = Gson()
        val leagues = mutableListOf<FootballLeague>()
        for (i in 0 until jsonArray.length()) {
            val leagueObject = jsonArray.getJSONObject(i)
            val league = gson.fromJson(leagueObject.toString(), FootballLeague::class.java)
            leagues.add(league)
        }
        return leagues
    }


    fun getEquipmentDetails(id : Int){
        if(id == 0){
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = LeagueApi.lookUpEquipment(id)
                withContext(Dispatchers.Main) {
                    Log.e("checkData", "$response")
                    _equipmentResults.value = response
                }
            } catch(e : Exception) {
                Log.e("checkData", "$e")
            }
        }
    }

    fun filterTeams(query: String) {
        if (query.isEmpty()) {
            return
        }
        viewModelScope.launch {
            val filteredTeams = _teams.value?.filter { team ->
                team.name.contains(query, ignoreCase = true) || team.teamShort.contains(
                    query,
                    ignoreCase = true
                )
            }
            _teams.value = filteredTeams
        }
    }

}