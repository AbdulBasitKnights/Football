package com.football.league.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryLeagues(
    @SerializedName("teams")
    val teams : List<TeamDetailsResponse>
)

data class TeamDetailsResponse(
    @SerializedName("idTeam")
    val idTeam: String,
    @SerializedName("strTeam")
    val name: String,
    @SerializedName("strTeamShort")
    val teamShort: String,
    @SerializedName("strAlternate")
    val alternateName: String,
    @SerializedName("intFormedYear")
    val formedYear: String,
    @SerializedName("strLeague")
    val league: String,
    @SerializedName("idLeague")
    val idLeague: String,
    @SerializedName("strStadium")
    val stadium: String,
    @SerializedName("strKeywords")
    val keywords: String,
    @SerializedName("intStadiumCapacity")
    val stadiumCapacity: String,
    @SerializedName("strWebsite")
    val website: String,
    @SerializedName("strKit")
    val strKit: String,
    @SerializedName("strBadge")
    val strBadge: String,
    @SerializedName("strCountry")
    val strCountry: String,
)
