package com.football.league.data.datasource.remote.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.football.league.data.datasource.remote.dto.LeagueData

@Dao
interface LeagueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(leagues: List<LeagueData>)
}