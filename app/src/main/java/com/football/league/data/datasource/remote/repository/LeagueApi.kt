package com.football.league.data.datasource.remote.repository

import android.util.Log
import com.football.league.data.datasource.remote.dto.CountryLeagues
import com.football.league.data.datasource.remote.dto.EquipmentModel
import com.football.league.data.datasource.remote.dto.LookUpEquipmentModel
import com.football.league.data.datasource.remote.dto.TeamDetailsResponse
import com.football.league.data.datasource.remote.network.NetworkHelper
import com.football.league.ui.utils.ApiEndpoints
import org.json.JSONObject

object LeagueApi {

      fun searchAllLeagues(leagues: String): CountryLeagues? {
        val url = "${ApiEndpoints.BASE_URL}${ApiEndpoints.SEARCH_ALL_TEAMS}?l=$leagues"
        val jsonResponse = NetworkHelper.makeGetRequest(url)
        return jsonResponse?.let { parseCountryLeagues(it) }
    }

     fun lookUpEquipment(id: Int): EquipmentModel? {
        val url = "${ApiEndpoints.BASE_URL}${ApiEndpoints.LOOKUP_EQUIPMENT}?id=$id"
        val jsonResponse = NetworkHelper.makeGetRequest(url)
        return jsonResponse?.let { parseEquipmentModel(it) }
    }

    private fun parseCountryLeagues(jsonResponse: String): CountryLeagues? {
        val jsonObject = JSONObject(jsonResponse)
        val teamsJsonArray = jsonObject.getJSONArray("teams")

        val teamsList = mutableListOf<TeamDetailsResponse>()
        for (i in 0 until teamsJsonArray.length()) {
            val teamJson = teamsJsonArray.getJSONObject(i)
            val teamDetails = TeamDetailsResponse(
                idTeam = teamJson.getString("idTeam"),
                name = teamJson.getString("strTeam"),
                teamShort = teamJson.getString("strTeamShort"),
                alternateName = teamJson.getString("strAlternate"),
                formedYear = teamJson.getString("intFormedYear"),
                league = teamJson.getString("strLeague"),
                idLeague = teamJson.getString("idLeague"),
                stadium = teamJson.getString("strStadium"),
                keywords = teamJson.getString("strKeywords"),
                stadiumCapacity = teamJson.getString("intStadiumCapacity"),
                website = teamJson.getString("strWebsite"),
                        strKit = teamJson.getString("strKit"),
                        strBadge = teamJson.getString("strBadge"),
                        strCountry = teamJson.getString("strCountry")
            )
            teamsList.add(teamDetails)
        }

        return CountryLeagues(teamsList)
    }


    private fun parseEquipmentModel(jsonResponse: String): EquipmentModel? {
        val jsonObject = JSONObject(jsonResponse)
        val equipmentJsonArray = jsonObject.getJSONArray("equipment")

        val equipmentList = mutableListOf<LookUpEquipmentModel>()
        for (i in 0 until equipmentJsonArray.length()) {
            val equipmentJson = equipmentJsonArray.getJSONObject(i)
            val equipmentModel = LookUpEquipmentModel(
                idEquipment = equipmentJson.getString("idEquipment"),
                idTeam = equipmentJson.getString("idTeam"),
                season = equipmentJson.getString("strSeason"),
                equipmentImageUrl = equipmentJson.getString("strEquipment"),
                date =equipmentJson.getString("date"),
                type = equipmentJson.getString("strType"),
                username =equipmentJson.getString("strUsername"),

            )
            equipmentList.add(equipmentModel)
            Log.e("checkData","${equipmentModel.equipmentImageUrl}")
        }

        return EquipmentModel(equipmentList)
    }


}