package com.raywenderlich.kotlin.coroutines.utils

sealed class ScreenState<out T> {
  object Loading : ScreenState<Nothing>()
  class Render<T>(val data: T) : ScreenState<T>()
}