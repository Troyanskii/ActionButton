package com.troy.actionbutton.network

import com.troy.actionbutton.model.Action
import retrofit2.Call
import retrofit2.Callback
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RESTClient(private val restService: RESTService) {

    suspend fun getAllActions(): Response<List<Action>> = suspendCoroutine { continuation ->
        restService.getActions().enqueue(object : Callback<List<Action>> {
            override fun onResponse(call: Call<List<Action>>, response: Response<List<Action>>) {
                response.body()?.let {
                    continuation.resume(Response.Success(it))
                } ?: continuation.resume(Response.Error("Something went wrong."))
            }

            override fun onFailure(call: Call<List<Action>>, t: Throwable) {
                continuation.resume(Response.Error(t.message!!))
            }
        })
    }
}