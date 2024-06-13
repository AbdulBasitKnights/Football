package com.football.league.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName


data class TeamData(
    @SerializedName("equipment")
    val equipment : List<TeamJercy>
)
data class TeamJercy(
    @SerializedName("idEquipment")
    val idEquipment: String,
    @SerializedName("idTeam")
    val idTeam: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("strSeason")
    val season: String,
    @SerializedName("strEquipment")
    val equipmentImageUrl: String,
    @SerializedName("strType")
    val type: String,
    @SerializedName("strUsername")
    val username: String
)
