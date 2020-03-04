package com.raywenderlich.kotlin.coroutines.ui.movies

import com.raywenderlich.kotlin.coroutines.data.model.Movie

sealed class MoviesScreenState {
  class CurrentMovies(val movies: List<Movie>) : MoviesScreenState()
  class Error(val error: String) : MoviesScreenState()
}