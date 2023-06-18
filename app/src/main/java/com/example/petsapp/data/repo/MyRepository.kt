package com.example.petsapp.data.repo

import android.provider.ContactsContract.Profile
import com.example.petsapp.presentation.profile.ProfileData
import com.example.petsapp.utils.Response
import kotlinx.coroutines.flow.Flow

interface MyRepository {

    suspend fun tryLogin(
        login: String,
        password: String
    ): Flow<Response<String>>

    suspend fun tryRegister(
        login: String,
        password: String,
        email: String,
        username: String
    ): Flow<Response<String>>

    suspend fun getProfile(
        token: String
    ): Flow<Response<ProfileData>>
}