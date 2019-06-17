package com.kaspersky.kaspresso.logger

/**
 * The logger with caching for logging composite messages with 3 levels: info, debug and error.
 */
class CachedLogger(
    val logger: UiTestLogger
) {
    /**
     * Cached text for info level logging.
     */
    var cachedInfo: String? = null

    /**
     * Cached text for debug level logging.
     */
    var cachedDebug: String? = null

    /**
     * Cached text for error level logging.
     */
    var cachedError: String? = null

    /**
     * Info level of logging with cached text.
     */
    fun infoWithCache(text: String) {
        logger.i("${cachedInfo?.let { it } ?: ""} $text".trimStart())
        cachedInfo = null
    }

    /**
     * Debug level of logging with cached text.
     */
    fun debugWithCache(text: String) {
        logger.d("${cachedDebug?.let { it } ?: ""} $text".trimStart())
        cachedDebug = null
    }

    /**
     * Error level of logging with cached text.
     */
    fun errorWithCache(text: String) {
        logger.e("${cachedError?.let { it } ?: ""} $text".trimStart())
        cachedError = null
    }

    /**
     * Info level of logging with cached text with tag.
     */
    fun infoWithCache(tag: String, text: String) {
        logger.i(tag, "${cachedInfo?.let { it } ?: ""} $text".trimStart())
        cachedInfo = null
    }

    /**
     * Debug level of logging with cached text with tag.
     */
    fun debugWithCache(tag: String, text: String) {
        logger.d(tag, "${cachedDebug?.let { it } ?: ""} $text".trimStart())
        cachedDebug = null
    }

    /**
     * Error level of logging with cached text with tag.
     */
    fun errorWithCache(tag: String, text: String) {
        logger.e(tag, "${cachedError?.let { it } ?: ""} $text".trimStart())
        cachedError = null
    }
}