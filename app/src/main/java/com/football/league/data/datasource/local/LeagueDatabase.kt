package com.football.league.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.football.league.data.datasource.remote.dto.LeagueData
import com.football.league.data.datasource.remote.dto.SearchLeagueEntity
import com.football.league.data.datasource.remote.repository.LeagueDao
import com.football.league.data.datasource.remote.repository.SearchLeagueDao

@Database(entities = [LeagueData::class, SearchLeagueEntity::class], version = 3)
abstract class LeagueDatabase : RoomDatabase() {

    abstract fun footballLeagueDao() : LeagueDao
    abstract fun searchLeagueDao() : SearchLeagueDao
}