package com.football.league.data.datasource.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
data class League(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0, // Auto-generated primary key
    val idLeague: String,
    val name: String?, // Nullable name field
    val strSport: String,
    val strLeagueAlternate: String
)
