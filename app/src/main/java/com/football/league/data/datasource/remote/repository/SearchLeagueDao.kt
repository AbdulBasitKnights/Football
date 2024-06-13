package com.football.league.data.datasource.remote.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.football.league.data.datasource.remote.dto.SearchLeagueEntity

@Dao
interface SearchLeagueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<SearchLeagueEntity>)

    @Query("SELECT * FROM search_leagues WHERE name LIKE '%' || :searchQuery || '%' OR league LIKE '%' || :searchQuery || '%'")
    suspend fun searchClubsByPartialNameOrLeague(searchQuery: String): List<SearchLeagueEntity>
}