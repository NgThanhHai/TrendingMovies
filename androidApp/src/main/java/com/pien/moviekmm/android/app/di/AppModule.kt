package com.pien.moviekmm.android.app.di

import android.content.Context
import com.pien.moviekmm.core.data.local.LocalMovieDetailsDataSource
import com.pien.moviekmm.core.data.local.LocalTrendingMoviesDataSource
import com.pien.moviekmm.core.data.mapper.MovieDetailMapper
import com.pien.moviekmm.core.data.mapper.MovieMapper
import com.pien.moviekmm.core.data.repository.MovieDetailRepository
import com.pien.moviekmm.core.data.repository.MovieDetailRepositoryImpl
import com.pien.moviekmm.core.data.repository.TrendingMovieRepository
import com.pien.moviekmm.core.data.repository.TrendingMovieRepositoryImpl
import com.pien.moviekmm.core.database.MovieDatabase
import com.pien.moviekmm.core.database.daos.MovieDao
import com.pien.moviekmm.core.database.daos.MovieDetailDao
import com.pien.moviekmm.core.domain.usecase.GetMovieDetailUseCase
import com.pien.moviekmm.core.domain.usecase.GetTrendingMoviesUseCase
import com.pien.moviekmm.core.domain.usecase.SearchTrendingMoviesUseCase
import com.pien.moviekmm.core.data.network.HttpClientFactory
import com.pien.moviekmm.core.data.network.RemoteMovieDetailDataSource
import com.pien.moviekmm.core.data.network.RemoteTrendingMoviesDataSource
import com.pien.moviekmm.core.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideRemoteTrendingMoviesDataSource(httpClient: HttpClient): RemoteTrendingMoviesDataSource {
        return RemoteTrendingMoviesDataSource(httpClient)
    }
    @Provides
    @Singleton
    fun provideLocalTrendingMoviesDataSource(movieDao: MovieDao, mapper: MovieMapper): LocalTrendingMoviesDataSource {
        return LocalTrendingMoviesDataSource(movieDao, mapper)
    }

    @Provides
    @Singleton
    fun provideTrendingMovieRepository(remoteDataSource: RemoteTrendingMoviesDataSource,
        localDataSource: LocalTrendingMoviesDataSource): TrendingMovieRepository{
        return TrendingMovieRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideRemoteMovieDetailDataSource(httpClient: HttpClient): RemoteMovieDetailDataSource {
        return RemoteMovieDetailDataSource(httpClient)
    }

    @Provides
    @Singleton
    fun provideLocalMovieDetailDataSource(movieDetailDao: MovieDetailDao, movieDetailMapper: MovieDetailMapper): LocalMovieDetailsDataSource {
        return LocalMovieDetailsDataSource(movieDetailDao, movieDetailMapper)
    }

    @Provides
    @Singleton
    fun provideMovieDetailRepository(remoteDataSource: RemoteMovieDetailDataSource,
        localDataSource: LocalMovieDetailsDataSource): MovieDetailRepository {
        return MovieDetailRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideGetTrendingMovieUseCase(repository: TrendingMovieRepository): GetTrendingMoviesUseCase {
        return GetTrendingMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSearchTrendingMovieUseCase(repository: TrendingMovieRepository): SearchTrendingMoviesUseCase {
        return SearchTrendingMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailUseCase(repository: MovieDetailRepository): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Database.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.getMovieDao()
    }

    @Provides
    @Singleton
    fun provideMovieDetailDao(database: MovieDatabase): MovieDetailDao {
        return database.getMovieDetailDao()
    }

    @Provides
    @Singleton
    fun provideMovieMapper(): MovieMapper {
        return MovieMapper
    }

    @Provides
    @Singleton
    fun provideMovieDetailMapper(): MovieDetailMapper {
        return MovieDetailMapper
    }
}