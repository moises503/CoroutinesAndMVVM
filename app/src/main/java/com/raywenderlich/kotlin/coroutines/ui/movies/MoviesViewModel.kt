package com.raywenderlich.kotlin.coroutines.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raywenderlich.kotlin.coroutines.domain.repository.MovieRepository
import com.raywenderlich.kotlin.coroutines.utils.ScreenState
import com.raywenderlich.kotlin.coroutines.utils.logCoroutine
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {
  private lateinit var _currentMovies : MutableLiveData<ScreenState<MoviesScreenState>>

  val currentMovies: LiveData<ScreenState<MoviesScreenState>> get() {
    if (!::_currentMovies.isInitialized){
      _currentMovies = MutableLiveData()
      getCurrentMovies()
    }
    return _currentMovies
  }

  private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
    throwable.printStackTrace()
  }

  fun getCurrentMovies() {
    viewModelScope.launch(coroutineExceptionHandler) {
      _currentMovies.value = ScreenState.Loading
      logCoroutine("getData", coroutineContext)
      val result = runCatching { movieRepository.getMovies() }
      Log.d("TestCoroutine", "Still Alive!")
      result.onSuccess { movies ->
        _currentMovies.value = ScreenState.Render(MoviesScreenState.CurrentMovies(movies))
      }.onFailure { error ->
        _currentMovies.value = ScreenState.Render(MoviesScreenState.Error(error.message ?: "Error"))
      }
    }
  }
}