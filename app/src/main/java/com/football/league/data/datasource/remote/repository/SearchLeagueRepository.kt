package com.football.league.data.datasource.remote.repository

import com.football.league.data.datasource.remote.dto.CountryLeagues
import com.football.league.data.datasource.remote.dto.SearchLeagueEntity
import javax.inject.Inject

class SearchLeagueRepository @Inject constructor(private val searchLeagueDao: SearchLeagueDao) {

    suspend fun saveSearchLeagues(leagues : CountryLeagues){
            val teams = leagues.teams.map { team ->
                SearchLeagueEntity(
                    idTeam = team.idTeam,
                    name = team.name,
                    teamShort = team.teamShort,
                    alternateName = team.alternateName,
                    formedYear = team.formedYear,
                    league = team.league,
                    idLeague = team.idLeague,
                    stadium = team.stadium,
                    keywords = team.keywords,
                    stadiumCapacity = team.stadiumCapacity,
                    website = team.website,
                    teamJersey = team.strKit,
                    strCountry = team.strCountry,
                    teamLogo = team.strBadge


                )
            }
        return searchLeagueDao.insertTeams(teams)
    }

    suspend fun searchByPartialNameOrLeague(searchQuery : String) : List<SearchLeagueEntity>{
        return searchLeagueDao.searchClubsByPartialNameOrLeague(searchQuery)
    }
}