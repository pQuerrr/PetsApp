package com.example.petsapp.presentation.login

import android.media.session.MediaSession.Token
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petsapp.data.repo.IPreferences
import com.example.petsapp.data.repo.MyRepository
import com.example.petsapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: MyRepository,
    private val preferences: IPreferences
) : ViewModel() {

    private val _loginState = mutableStateOf<Response<String>>(Response.Success(""))
    val loginState: State<Response<String>> = _loginState

    fun tryLogin(login: String, password: String) {
        viewModelScope.launch {
            repository.tryLogin(login, password).collect { response ->
                _loginState.value = response
                if (response is Response.Success && response.data != null) setToken(response.data)
            }
        }
    }

    fun setToken(token: String) {
        viewModelScope.launch { preferences.setToken(token) }
    }

    val tokenState = mutableStateOf<String?>(null)

    fun getToken() {
        viewModelScope.launch {
            tokenState.value = preferences.getToken()
        }
    }

}