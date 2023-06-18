package com.example.petsapp.presentation.profile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petsapp.data.repo.IPreferences
import com.example.petsapp.data.repo.MyRepository
import com.example.petsapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MyRepository,
    private val preferences: IPreferences
) : ViewModel() {

    private val _profileState =
        mutableStateOf<Response<ProfileData>>(Response.Success(ProfileData()))
    val profileState: State<Response<ProfileData>> = _profileState
    val tokenState = mutableStateOf<String?>(null)

    init {
        getToken()
    }

    suspend fun getProfile(token: String) {
        repository.getProfile(token).collect() { response ->
            _profileState.value = response

        }
    }


    fun getToken() {
        viewModelScope.launch {
            tokenState.value = preferences.getToken()
            tokenState.value?.let { getProfile(it) }
        }
    }

}


