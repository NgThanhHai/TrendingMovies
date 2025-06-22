package com.pien.moviekmm.android.features.moviedetails.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pien.moviekmm.core.domain.usecase.GetMovieDetailUseCase
import com.pien.moviekmm.features.moviedetails.MovieDetailEvent
import com.pien.moviekmm.features.moviedetails.MovieDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AndroidMovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    @ApplicationContext private val context: Context): ViewModel() {

        private val viewModel by lazy {
            MovieDetailViewModel(
                getMovieDetailUseCase = getMovieDetailUseCase,
                coroutineScope = viewModelScope)
        }

    val state = viewModel.state

    fun onEvent(event: MovieDetailEvent) {
        viewModel.onEvent(event, isNetworkAvailable = isNetworkAvailable())
    }

    private fun isNetworkAvailable(): Boolean {
        val activeNetworkInfo: NetworkInfo? = (getSystemService(context, ConnectivityManager::class.java))?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}