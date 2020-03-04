package com.raywenderlich.kotlin.coroutines.contextProvider

import kotlin.coroutines.CoroutineContext

/**
 * Provides a context for coroutines, abstracting it away.
 */
interface CoroutineContextProvider {

  fun context(): CoroutineContext
}