package com.football.league.data.datasource.remote.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.football.league.data.datasource.remote.dto.FootballLeague

@Dao
interface FootballLeagueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(leagues: List<FootballLeague>)
}