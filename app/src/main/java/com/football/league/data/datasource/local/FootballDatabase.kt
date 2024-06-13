package com.football.league.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.football.league.data.datasource.remote.dto.FootballLeague
import com.football.league.data.datasource.remote.dto.SearchLeagueEntity
import com.football.league.data.datasource.remote.repository.FootballLeagueDao
import com.football.league.data.datasource.remote.repository.SearchLeagueDao

@Database(entities = [FootballLeague::class, SearchLeagueEntity::class], version = 3)
abstract class FootballDatabase : RoomDatabase() {

    abstract fun footballLeagueDao() : FootballLeagueDao
    abstract fun searchLeagueDao() : SearchLeagueDao
}