package com.example.petsapp.presentation.registration

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.petsapp.R
import com.example.petsapp.presentation.login.DiplomField
import com.example.petsapp.presentation.login.showToast


@Composable
fun Registration(
    viewModel: RegistrationViewModel = hiltViewModel(),
    onSingUpClick: () -> Unit,
    nextScreen: () -> Unit
) {


    val localContext = LocalContext.current

    var loginReg by remember {
        mutableStateOf("")
    }

    var passwordReg by remember {
        mutableStateOf("")
    }

    var usernameReg by remember {
        mutableStateOf("")
    }

    var emailReq by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.login_bg),
            contentDescription = "Login",
            modifier = Modifier
                .fillMaxSize()
                .blur(6.dp),
            contentScale = ContentScale.Crop
        )
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(28.dp)
        .alpha(0.6f)
        .clip(
            CutCornerShape(
                topStart = 8.dp,
                topEnd = 16.dp,
                bottomStart = 16.dp,
                bottomEnd = 8.dp
            )
        )
        .background(MaterialTheme.colors.background)
    )
    Column(
        Modifier
            .fillMaxSize()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        RegistrationHeader()
        RegistrationFields(loginReg,passwordReg,emailReq,usernameReg,onLoginChange = {loginReg = it}, onPasswordChange = {passwordReg = it}, onEmailChange = {emailReq = it}, onUsernameChange = {usernameReg = it})
        RegistrationFooter(onSingUpClick = onSingUpClick,RegistrationClick = {
            when {
                loginReg.isBlank() -> {
                    localContext.showToast("Введите логин")
                }
                passwordReg.isBlank() -> {
                    localContext.showToast("Введите пароль")
                }
                emailReq.isBlank() -> {
                   localContext.showToast("Введите логин")
                }

                usernameReg.isBlank() -> {
                    localContext.showToast("Введите пароль")
                }
                else -> {
                   viewModel.tryRegister(loginReg,passwordReg,emailReq,usernameReg)
                    viewModel.getToken()
                    if (!viewModel.tokenState.value.isNullOrEmpty()) {
                        nextScreen()
                    }
                }
            }
        }
        )
        }
    }


@Composable
fun RegistrationHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Регистрация!", fontSize = 31.sp, fontWeight = FontWeight.ExtraBold)
        Text(text = "Зарегистрируйтесь чтобы продолжить",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RegistrationFields(
    loginReg:String,
    passwordReg: String,
    emailReg:String,
    usernameReg: String,
    onLoginChange:(String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onEmailChange:(String) -> Unit,
    onUsernameChange:(String) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        DiplomField(value = loginReg,
            label = "Логин",
            placeholder = "Введите логин",
            onValueChange = onLoginChange,
            leadingIcon = {
                Icon(Icons.Default.Star, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(8.dp))

        DiplomField(value = passwordReg,
            label = "Пароль",
            placeholder = "Введите пароль",
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Go)
        )
        DiplomField(value = emailReg,
            label = "Email",
            placeholder = "Введите email адрес",
            onValueChange = onEmailChange,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )
        DiplomField(value = usernameReg,
            label = "Имя пользователя",
            placeholder = "Введите имя пользователя",
            onValueChange = onUsernameChange,
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )
        }

    }
@Composable
fun RegistrationFooter(
    RegistrationClick: () -> Unit,
    onSingUpClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = RegistrationClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "Зарегистрироваться")
        }
        TextButton(onClick = onSingUpClick) {
            Text(text = "Есть аккаунт, нажмите здесь!")
        }
    }
}




