package com.example.petsapp.presentation.forum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petsapp.domain.models.Theme
import com.example.petsapp.presentation.view.DefaultButton
import com.example.petsapp.presentation.view.DefaultTextField
import com.example.petsapp.presentation.view.TabsHeader
import java.time.LocalDateTime

@Composable
fun AddThemeScreen(creatorName: String, onButtonClicked: (Theme) -> Unit) {
    val themeItem = remember {
        mutableStateOf(
            Theme(dateTime = LocalDateTime.now(), creatorUsername = creatorName)
        )
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TabsHeader(text = "Добавить тему")
        DefaultTextField(
            Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
            onTextChanged = {
                themeItem.value = themeItem.value.copy(name = it)
            },
            placeholderText = "Введите название темы"
        )
        DefaultTextField(
            Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
            onTextChanged = {
                themeItem.value = themeItem.value.copy(description = it)
            },
            placeholderText = "Введите описание темы",
            maxLines = 7
        )
        DefaultButton(modifier = Modifier.fillMaxWidth().height(100.dp).padding(horizontal = 20.dp, vertical = 15.dp), onButtonClicked = { onButtonClicked(themeItem.value) }, buttonText = "Добавить")
    }
}

@Composable
@Preview
fun AddThemeScreenPreview() {
    AddThemeScreen(creatorName = "Попа)", onButtonClicked = {})
}

