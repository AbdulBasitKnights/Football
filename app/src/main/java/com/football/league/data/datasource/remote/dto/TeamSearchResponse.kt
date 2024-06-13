package com.football.league.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class TeamSearchResponse(
    @SerializedName("teams")
    val teams: List<Team>
)

data class Team(
    @SerializedName("idTeam")
    val idTeam: String,
    @SerializedName("strTeam")
    val name: String,
    @SerializedName("strTeamAlternate")
    val alternate : String

)
