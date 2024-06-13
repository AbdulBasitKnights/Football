package com.football.league.di

import android.content.Context
import androidx.room.Room
import com.football.league.data.datasource.local.FootballDatabase
import com.football.league.data.datasource.remote.repository.FootballLeagueDao
import com.football.league.data.datasource.remote.repository.FootballLeagueRepository
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
    fun provideFootballDatabase(@ApplicationContext context: Context): FootballDatabase {
        return Room.databaseBuilder(
            context,
            FootballDatabase::class.java,
            "football_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFootballLeagueDao(database: FootballDatabase): FootballLeagueDao {
        return database.footballLeagueDao()
    }

    @Provides
    @Singleton
    fun provideSearchLeagueDao(database: FootballDatabase): SearchLeagueDao {
        return database.searchLeagueDao()
    }

    @Provides
    @Singleton
    fun provideFootballRepository(footballLeagueDao: FootballLeagueDao) : FootballLeagueRepository {
        return FootballLeagueRepository(footballLeagueDao)
    }

    @Provides
    @Singleton
    fun provideSearchLeagueRepository(searchLeagueDao: SearchLeagueDao) : SearchLeagueRepository {
        return SearchLeagueRepository(searchLeagueDao)
    }
}