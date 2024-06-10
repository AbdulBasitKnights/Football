package com.football.league.data.datasource.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clubs")
data class Club(
    @PrimaryKey val idTeam: String,
    val name: String,
    val strTeamShort: String?,
    val strAlternate: String?,
    val intFormedYear: Int?,
    val strLeague: String?,
    val idLeague: String?,
    val strStadium: String?,
    val strKeywords: String?,
    val strStadiumThumb: String?,
    val strStadiumLocation: String?,
    val intStadiumCapacity: Int?,
    val strWebsite: String?,
    val strTeamJersey: String?,
    val strTeamLogo: String?
)