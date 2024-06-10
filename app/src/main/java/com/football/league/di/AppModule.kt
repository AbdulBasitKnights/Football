package com.football.league.di

import android.content.Context
import androidx.room.Room
import com.football.league.data.datasource.local.AppDatabase
import com.football.league.data.datasource.local.LeagueDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "sports.db"
        ).build()
    }
    @Provides
    fun provideLeagueDao(db: AppDatabase): LeagueDao {
        return db.leagueDao()
    }
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO)
    }
}