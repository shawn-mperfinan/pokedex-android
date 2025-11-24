package dev.mperfinan.pokedex.data.network.adapter

import dev.mperfinan.pokedex.data.network.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkResultCallAdapter<T : Any>(
    private val resultType: Type,
) : CallAdapter<T, Call<ApiResult<T>>> {
    override fun responseType(): Type = resultType

    override fun adapt(call: Call<T>): Call<ApiResult<T>> = NetworkResultCall(call)
}
