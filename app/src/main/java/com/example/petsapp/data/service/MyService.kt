package com.example.petsapp.data.service

import com.example.petsapp.domain.models.LoginQueryBody
import com.example.petsapp.domain.models.RegisterQueryBody
import com.google.gson.internal.LinkedTreeMap
import retrofit2.http.Body
import retrofit2.http.POST

interface MyService {

    @POST("login")
    suspend fun tryLogin(
        @Body loginQueryBody: LoginQueryBody
    ): LinkedTreeMap<String, String>

    @POST("register")
    suspend fun tryRegister(
        @Body registerQueryBody: RegisterQueryBody
    ): LinkedTreeMap<String, String>
}