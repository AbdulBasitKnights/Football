package com.football.league.data.datasource.remote.repository

import com.football.league.data.datasource.remote.dto.CountryLeagues
import com.football.league.data.datasource.remote.dto.EquipmentModel
import javax.inject.Inject

class FootballApiRepositoryImp @Inject constructor(private val footballApi: FootballApi) :
    FootballApi {
    override suspend fun searchAllLeagues(league: String): CountryLeagues {
        return footballApi.searchAllLeagues(leagues = league)
    }

    override suspend fun lookUpEquipment(idTeam: Int): EquipmentModel {
        return footballApi.lookUpEquipment(idTeam)
    }
}