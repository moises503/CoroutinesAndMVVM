/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.kotlin.coroutines.ui.movies

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.kotlin.coroutines.R
import com.raywenderlich.kotlin.coroutines.utils.ScreenState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

  private val moviesViewModel by viewModel<MoviesViewModel>()
  private val movieAdapter by lazy { MovieAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initUi()
  }


  private fun initUi() {
    moviesList.adapter = movieAdapter
    moviesList.layoutManager = LinearLayoutManager(this)
    swipeToRefresh.setOnRefreshListener { moviesViewModel.getCurrentMovies() }
    moviesViewModel.currentMovies.observe(this, Observer { screenState ->
      renderUI(screenState)
    })
  }

  private fun renderUI(screenState: ScreenState<MoviesScreenState>) {
    when(screenState){
      ScreenState.Loading -> showLoader()
      is ScreenState.Render -> showCurrentMovieList(screenState.data)
    }
  }

  private fun showCurrentMovieList(moviesScreenState: MoviesScreenState){
    hideLoader()
    when(moviesScreenState){
      is MoviesScreenState.CurrentMovies -> movieAdapter.setData(moviesScreenState.movies)
      is MoviesScreenState.Error -> showError(moviesScreenState.error)
    }
  }

  private fun showLoader() {
    swipeToRefresh.isRefreshing = true
  }

  private fun hideLoader() {
    swipeToRefresh.isRefreshing = false
  }

  private fun showError(message : String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
  }
}


