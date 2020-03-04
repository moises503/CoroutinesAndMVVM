package com.raywenderlich.kotlin.coroutines.data.model

/**
 * Represents either a value or a throwable, from an API response.
 */
class Result<out T>(val value: T?, val throwable: Throwable?)