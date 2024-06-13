package com.football.league.data.datasource.remote.repository

import com.football.league.data.datasource.remote.dto.FootballLeague
import javax.inject.Inject

class FootballLeagueRepository @Inject constructor(private val footballLeagueDao: FootballLeagueDao) {

    suspend fun insertFromJson(league : List<FootballLeague>){
        return footballLeagueDao.insertAll(league)
    }
}