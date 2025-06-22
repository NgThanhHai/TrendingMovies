package com.pien.moviekmm.android.features.trendingmovies.presentation

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pien.moviekmm.core.domain.usecase.GetTrendingMoviesUseCase
import com.pien.moviekmm.core.domain.usecase.SearchTrendingMoviesUseCase
import com.pien.moviekmm.features.trendingmovies.TrendingMovieEvent
import com.pien.moviekmm.features.trendingmovies.TrendingMovieViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AndroidTrendingMovieViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val searchTrendingMoviesUseCase: SearchTrendingMoviesUseCase,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val viewModel by lazy {
        TrendingMovieViewModel(
            getTrendingMoviesUseCase = getTrendingMoviesUseCase,
            searchTrendingMoviesUseCase = searchTrendingMoviesUseCase,
            coroutineScope = viewModelScope)
    }

    init {
        viewModel.onEvent(TrendingMovieEvent.UpdateTrendingMovies, isNetworkAvailable())
    }

    val state = viewModel.state

    fun onEvent(event: TrendingMovieEvent) {
        viewModel.onEvent(event, isNetworkAvailable())
    }

    private fun isNetworkAvailable(): Boolean {
        val activeNetworkInfo: NetworkInfo? = (getSystemService(context, ConnectivityManager::class.java))?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}