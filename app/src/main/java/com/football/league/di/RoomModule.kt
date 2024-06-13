package com.football.league.di

import android.content.Context
import androidx.room.Room
import com.football.league.data.datasource.local.LeagueDatabase
import com.football.league.data.datasource.remote.repository.LeagueDao
import com.football.league.data.datasource.remote.repository.LeagueRepository
import com.football.league.data.datasource.remote.repository.SearchLeagueDao
import com.football.league.data.datasource.remote.repository.SearchLeagueRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideFootballDatabase(@ApplicationContext context: Context): LeagueDatabase {
        return Room.databaseBuilder(
            context,
            LeagueDatabase::class.java,
            "football_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFootballLeagueDao(database: LeagueDatabase): LeagueDao {
        return database.footballLeagueDao()
    }

    @Provides
    @Singleton
    fun provideSearchLeagueDao(database: LeagueDatabase): SearchLeagueDao {
        return database.searchLeagueDao()
    }

    @Provides
    @Singleton
    fun provideFootballRepository(leagueDao: LeagueDao) : LeagueRepository {
        return LeagueRepository(leagueDao)
    }

    @Provides
    @Singleton
    fun provideSearchLeagueRepository(searchLeagueDao: SearchLeagueDao) : SearchLeagueRepository {
        return SearchLeagueRepository(searchLeagueDao)
    }
}