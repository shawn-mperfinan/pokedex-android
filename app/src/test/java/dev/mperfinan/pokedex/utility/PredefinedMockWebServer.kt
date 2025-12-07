package dev.mperfinan.pokedex.utility

import dev.mperfinan.pokedex.data.network.adapter.NetworkResultCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

open class PredefinedMockWebServer<T> {
    private lateinit var mockWebServer: MockWebServer

    @BeforeEach
    fun startMockServer() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @AfterEach
    fun shutdownServer() {
        mockWebServer.shutdown()
    }

    fun createService(serviceClass: Class<T>): T {
        val okHttpClient = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResultCallAdapterFactory.Companion.create())
            .build()
            .create(serviceClass)
    }

    fun enqueueResponse(
        fileName: String,
        responseCode: Int,
    ) {
        enqueueResponse(fileName, emptyMap(), responseCode)
    }

    private fun enqueueResponse(
        fileName: String,
        headers: Map<String, String>,
        statusCode: Int,
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("mock-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse =
            MockResponse().apply {
                setBody(source.readString(StandardCharsets.UTF_8))
                setResponseCode(statusCode)
            }
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse)
    }
}
