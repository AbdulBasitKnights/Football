package com.football.league.data.datasource.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_leagues")
data class SearchLeagueEntity(
    @PrimaryKey val idTeam: String,
    val name: String?,
    val teamShort: String?,
    val alternateName: String?,
    val formedYear: String?,
    val league: String?,
    val idLeague: String?,
    val stadium: String?,
    val keywords: String?,
    val stadiumCapacity: String?,
    val website: String?,
    val teamJersey: String?,
    val teamLogo: String?,
    val strCountry: String?
    )
