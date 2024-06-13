package com.football.league.data.datasource.remote.repository

import com.football.league.data.datasource.remote.dto.CountryLeagues
import com.football.league.data.datasource.remote.dto.TeamData


interface FootballApi {

    suspend fun searchAllLeagues(leagues: String): CountryLeagues

    suspend fun lookUpEquipment(id: Int): TeamData

}