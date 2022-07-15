package ru.cft.shift.scheduler.utils

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallbackBuilder<T> private constructor() {

    private var onResponse: (Call<T>, Response<T>) -> Unit = { _, _ ->}
    private var onFailure: (Call<T>, Throwable) -> Unit = { _, t -> t.message?.let { Log.d("Error", it) } }

    companion object {

        fun <T> create() = CallbackBuilder<T>()

    }

    fun onResponse(action: (Call<T>, Response<T>) -> Unit): CallbackBuilder<T> {
        onResponse = action
        return this
    }

    fun onFailure(action: (Call<T>, Throwable) -> Unit): CallbackBuilder<T> {
        onFailure = action
        return this
    }

    fun build(): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>)
            = onResponse.invoke(call, response)

            override fun onFailure(call: Call<T>, t: Throwable)
            = onFailure.invoke(call, t)
        }
    }
}