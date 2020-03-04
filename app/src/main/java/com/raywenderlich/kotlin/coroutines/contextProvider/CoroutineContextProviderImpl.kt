package com.raywenderlich.kotlin.coroutines.contextProvider

import kotlin.coroutines.CoroutineContext

/**
 * Provides a context for coroutines, abstracting it away.
 * Use this to switch out contexts for entire layers, when needed.
 */
class CoroutineContextProviderImpl(
  private val context: CoroutineContext
) : CoroutineContextProvider {

  override fun context(): CoroutineContext = context
}