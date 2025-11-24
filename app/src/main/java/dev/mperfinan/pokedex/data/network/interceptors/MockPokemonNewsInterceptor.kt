package dev.mperfinan.pokedex.data.network.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

private const val STATUS_OK = 200

/**
 * An [Interceptor] used for mocking network responses during development or testing of Pokemon news endpoints.
 *
 * It intercepts specific API paths and returns predefined JSON responses loaded from the
 * application's assets folder, bypassing actual network calls.
 *
 * @param context The application context required to access asset files.
 */
class MockPokemonNewsInterceptor(
    private val context: Context,
) : Interceptor {
    /**
     * Intercepts the ongoing network request and potentially returns a mock response.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        return if (path.contains("api/1/us/news/get-news.json")) {
            val jsonResponse =
                context.assets.open("pokemon_news.json")
                    .bufferedReader()
                    .use { it.readText() }
                    .toResponseBody("application/json".toMediaType())

            // Build and return a mock HTTP 1.1 200 OK response
            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(STATUS_OK)
                .message("OK")
                .body(jsonResponse)
                .addHeader("Content-Type", "application/json")
                .build()
        } else {
            chain.proceed(request)
        }
    }
}
