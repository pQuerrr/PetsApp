package com.example.petsapp.presentation.login

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.petsapp.R



@Composable
fun Login(
    viewModel: LoginViewModel = hiltViewModel(),
    onSingUpClick: () -> Unit
) {

    val localContext = LocalContext.current

    var username by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }





    //val loginStatus by authViewModel.userLoginStatus.collectAsState()
    var showFailedDialog by remember{
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.login_bg),
            contentDescription = "Login",
            modifier = Modifier
                .fillMaxSize()
                .blur(6.dp),
            contentScale = ContentScale.Crop
        )

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
            LoginHeader()
            LoginFields(username,password, onPasswordChange = {
                password = it
            },
                onUsernameChange = {
                    username = it
                },
                onForgotPasswordClick = {

                })
            LoginFooter(onSingInClick = {
                when {
                    username.isBlank() -> {
                        localContext.showToast("Введите логин")
                    }

                    password.isBlank() -> {
                        localContext.showToast("Введите пароль")
                    }

                    else -> {
                       viewModel.tryLogin(username, password)

                    }
                }
            },
                onSingUpClick = onSingUpClick
            )
        }
    }

    if (showFailedDialog){
        //Показать диалоговое окно об ошибке
    }
}



//Высвечивать текст
fun Context.showToast(msg: String){
    Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
}


@Composable
fun LoginHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {


        Text(text = "Добро пожаловать!", fontSize = 31.sp, fontWeight = FontWeight.ExtraBold)
        Text(text = "Войдите чтобы продолжить",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}


@Composable
fun LoginFields(username:String,
                password: String,
                onUsernameChange:(String) -> Unit,
                onPasswordChange: (String) -> Unit,
                onForgotPasswordClick: () ->Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        DiplomField(value = username,
            label = "Email",
            placeholder = "Введите email адрес",
            onValueChange = onUsernameChange,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(8.dp))

        DiplomField(value = password,
            label = "Пароль",
            placeholder = "Введите пароль",
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Go)
        )

        TextButton(onClick = onForgotPasswordClick, modifier = Modifier.align(Alignment.End)) {
            Text(text = "Забыли пароль?")

        }
    }
}

@Composable
fun LoginFooter(
    onSingInClick: () -> Unit,
    onSingUpClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onSingInClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "Вход")
        }
        TextButton(onClick = onSingUpClick) {
            Text(text = "Нету аккаунта, нажмите здесь!")
        }
    }
}
//Поля ввода
@Composable
fun DiplomField(value: String,
                label: String,
                placeholder: String,
                visualTransformation: VisualTransformation = VisualTransformation.None,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                leadingIcon: @Composable (() -> Unit)? = null,
                trailingIcon: @Composable (() -> Unit)? = null,
                onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )


}



