package com.example.petsapp.data.repo

import android.util.Log
import com.example.petsapp.data.service.MyService
import com.example.petsapp.domain.models.LoginQueryBody
import com.example.petsapp.domain.models.ProfileQueryBody
import com.example.petsapp.domain.models.RegisterQueryBody
import com.example.petsapp.presentation.profile.ProfileData
import com.example.petsapp.utils.Response
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MyRepositoryImpl(
    private val myService: MyService
) : MyRepository {

    override suspend fun tryLogin(
        login: String,
        password: String
    ): Flow<Response<String>> = flow {
        try {
            emit(Response.Loading)
            val body = LoginQueryBody(login, password)
            val response: LinkedTreeMap<String, String> = myService.tryLogin(body)
            emit(Response.Success(response["token"]))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun tryRegister(
        login: String,
        password: String,
        email: String,
        username: String
    ): Flow<Response<String>> = flow {
        try {
            emit(Response.Loading)
            val body = RegisterQueryBody(login, password, email, username)
            val response: LinkedTreeMap<String, String> = myService.tryRegister(body)
            emit(Response.Success(response["token"]))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getProfile(
        token: String
    ): Flow<Response<ProfileData>> = flow {
        try {
            emit(Response.Loading)
            val body = ProfileQueryBody(token)
            val response: LinkedTreeMap<String, String> = myService.getProfile(body)
            emit(
                Response.Success(
                    ProfileData(
                        username = response["username"],
                        login = response["login"],
                        email = response["email"]
                    )
                )
            )
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

}

