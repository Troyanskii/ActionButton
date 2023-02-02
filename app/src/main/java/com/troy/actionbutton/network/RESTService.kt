package com.troy.actionbutton.network

import com.troy.actionbutton.model.Action
import retrofit2.Call
import retrofit2.http.GET

interface RESTService {

    @GET("androidexam/butto_to_action_config.json")
    fun getActions(): Call<List<Action>>

}