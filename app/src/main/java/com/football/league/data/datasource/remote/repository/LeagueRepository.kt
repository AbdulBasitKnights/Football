package com.football.league.data.datasource.remote.repository

import com.football.league.data.datasource.remote.dto.LeagueData
import javax.inject.Inject

class LeagueRepository @Inject constructor(private val leagueDao: LeagueDao) {

    suspend fun insertFromJson(league : List<LeagueData>){
        return leagueDao.insertAll(league)
    }
}