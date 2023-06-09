package com.example.petsapp.presentation.registration

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petsapp.data.repo.MyRepository
import com.example.petsapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: MyRepository
): ViewModel() {

    private val _registerState = mutableStateOf<Response<String>>(Response.Success(""))
    val registerState: State<Response<String>> = _registerState

    fun tryRegister(login: String, password: String, email: String, username:String ) {
        viewModelScope.launch {
            repository.tryRegister(login, password, email, username).collect { response ->
                _registerState.value = response
            }
        }
    }
}
