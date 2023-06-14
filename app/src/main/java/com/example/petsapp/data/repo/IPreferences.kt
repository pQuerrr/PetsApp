package com.example.petsapp.data.repo

interface IPreferences {
    suspend fun setToken(id: String?)
    suspend fun getToken(): String?
}