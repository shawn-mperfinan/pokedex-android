package dev.mperfinan.pokedex.data.network

import kotlinx.coroutines.delay
import java.io.IOException
import kotlin.random.Random

private const val DELAY_MIN_MS = 200L
private const val DELAY_MAX_MS = 1200L
private const val FAIL_RATE_PERCENT = 3
private const val SLOW_RATE_PERCENT = 10
private const val SEVERE_LAG_MIN_MS = 2500L
private const val SEVERE_LAG_MAX_MS = 4500L
private const val PERCENT_MAX_VALUE = 100

suspend fun simulatedNetworkDelay(
    minMs: Long = DELAY_MIN_MS,
    maxMs: Long = DELAY_MAX_MS,
    failRate: Int = FAIL_RATE_PERCENT,
    slowRate: Int = SLOW_RATE_PERCENT,
) {
    val randomTime = Random(System.nanoTime())

    // normal jitter delay
    delay(randomTime.nextLong(minMs, maxMs))

    // occasional severe lag
    if (randomTime.nextInt(PERCENT_MAX_VALUE) < slowRate) {
        delay(randomTime.nextLong(SEVERE_LAG_MIN_MS, SEVERE_LAG_MAX_MS))
    }

    // occasional error simulation
    if (randomTime.nextInt(PERCENT_MAX_VALUE) < failRate) {
        throw IOException("Simulated network error")
    }
}
