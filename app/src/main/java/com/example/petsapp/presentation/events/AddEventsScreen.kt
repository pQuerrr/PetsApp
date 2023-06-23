package com.example.petsapp.presentation.events

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petsapp.R
import com.example.petsapp.domain.models.Events
import com.example.petsapp.domain.models.Theme
import com.example.petsapp.presentation.view.DefaultButton
import com.example.petsapp.presentation.view.DefaultTextField
import com.example.petsapp.presentation.view.TabsHeader
import java.time.LocalDateTime

@Composable
fun AddEventsScreen(creatorName: String, onButtonClicked: (Events) -> Unit) {
    val image = painterResource(R.drawable.profile)
    val eventsItem = remember {
        mutableStateOf(
            Events(dateTime = LocalDateTime.now(), creatorUsername = creatorName, image = image))
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TabsHeader(text = "Создать мероприятие")
        DefaultTextField(
            Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
            onTextChanged = {
                eventsItem.value = eventsItem.value.copy(name = it)
            },
            placeholderText = "Введите название мероприятия",
        )
        DefaultTextField(
            Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
            onTextChanged = {
                eventsItem.value = eventsItem.value.copy(description = it)
            },
            placeholderText = "Введите описание мероприятия",
            maxLines = 7
        )
        DefaultButton(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 20.dp, vertical = 15.dp), onButtonClicked = { onButtonClicked(eventsItem.value) }, buttonText = "Добавить")
    }
}

@Composable
@Preview
fun AddEventsScreenPreview() {
    AddEventsScreen(creatorName = "Попа)", onButtonClicked = {})
}

