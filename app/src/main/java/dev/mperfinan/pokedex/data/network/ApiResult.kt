package dev.mperfinan.pokedex.data.network

/**
 * A sealed interface representing the result of an API operation.
 */
sealed interface ApiResult<out T> {
    /**
     * Represents a successful API call with the resulting [data].
     */
    class Success<T>(val data: T) : ApiResult<T>

    /**
     * Represents a failed API call due to a known error code and message.
     */
    class Error(val code: Int, val message: String?) : ApiResult<Nothing>

    /**
     * Represents a failure due to an unexpected [exception].
     */
    class Exception(val exception: Throwable) : ApiResult<Nothing>
}

/**
 * Executes the provided [executable] function if the [ApiResult] is [ApiResult.Success].
 */
suspend fun <T : Any> ApiResult<T>.onSuccess(executable: suspend (T) -> Unit): ApiResult<T> {
    if (this is ApiResult.Success<T>) executable(data)
    return this
}

/**
 * Executes the provided [executable] function if the [ApiResult] is [ApiResult.Error].
 */
suspend fun ApiResult<*>.onError(executable: suspend (code: Int, message: String?) -> Unit): ApiResult<*> {
    if (this is ApiResult.Error) executable(code, message)
    return this
}

/**
 * Executes the provided [executable] function if the [ApiResult] is [ApiResult.Exception].
 */
suspend fun ApiResult<*>.onException(executable: suspend (exception: Throwable) -> Unit): ApiResult<*> {
    if (this is ApiResult.Exception) executable(exception)
    return this
}
