package com.football.league.di

import com.football.league.data.datasource.remote.repository.LeagueApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
    @InstallIn(SingletonComponent::class)
    object ApiModule {

        @Provides
        @Singleton
        fun provideFootballApi(): LeagueApi {
            return LeagueApi
        }
    }
