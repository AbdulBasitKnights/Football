package com.football.league.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.football.league.data.datasource.remote.dto.Club
import com.football.league.data.datasource.remote.dto.League
import com.football.league.data.datasource.remote.dto.Response

@Dao
interface LeagueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(leagues: List<League>)

    @Query("SELECT * FROM leagues")
    suspend fun getAllLeagues(): List<League>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllClubs(clubs: List<Club>)

}
