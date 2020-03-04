package com.raywenderlich.kotlin.coroutines.utils

import android.util.Log
import kotlin.coroutines.CoroutineContext

/**
 * Helps log information about coroutines.
 */

fun logCoroutine(methodName: String, coroutineContext: CoroutineContext) {
  Log.d(
    "TestCoroutine", "Thread for $methodName is: ${Thread.currentThread().name}" +
        "and the context is $coroutineContext"
  )
}