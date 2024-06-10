package com.football.league.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.football.league.data.datasource.remote.dto.Club
import com.football.league.data.datasource.remote.dto.League

@Database(entities = [Club::class,League::class], version = 111)
abstract class AppDatabase : RoomDatabase() {
    abstract fun leagueDao(): LeagueDao
}