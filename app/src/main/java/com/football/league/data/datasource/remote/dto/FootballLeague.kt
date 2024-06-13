package com.football.league.data.datasource.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "football_leagues")
data class FootballLeague(
    @PrimaryKey val idLeague: String,
    val strLeague: String,
    val strSport: String,
    val strLeagueAlternate: String
)