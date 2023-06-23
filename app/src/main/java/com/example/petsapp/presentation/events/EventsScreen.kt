package com.example.petsapp.presentation.events

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.view.common.Toolbar
import com.example.petsapp.R
import com.example.petsapp.domain.models.Events
import com.example.petsapp.domain.models.Theme
import com.example.petsapp.presentation.forum.ThemeItem
import com.example.petsapp.presentation.view.DefaultButton
import com.example.petsapp.presentation.view.TabsHeader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun EventsScreen(
    toolbarDestinations: List<() -> Unit>
) {
    val firstImage = painterResource(R.drawable.firstimage)
    val secondImage = painterResource(R.drawable.secondimage)
    val isAddingOpened = remember { mutableStateOf(false) }

    val eventsList = remember {
        mutableStateListOf(
            //Тут какие темы будут
            Events(
                name = "Прогулка по парку",
                creatorUsername = "MarkProvorov",
                dateTime = LocalDateTime.of(2023, 7, 1,10,10,10),
                description = "Предлагаю собраться и прогуляться с собаками по парку Победы!",
                image = firstImage
            ),
            Events(
                name = "PetPalooza",
                creatorUsername = "MaxBozjenko",
                dateTime = LocalDateTime.of(2023, 5, 20,10,10,10),
                description = "PetPalooza - это уникальное мероприятие, посвященное всем видам домашних питомцев и предназначенное для всех любителей животных. ",
                image = secondImage
            )
        )
    }

    fun onEventsAdded(newEvents: Events) {
        eventsList.add(newEvents)
    }

    if (!isAddingOpened.value) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White)

        ) {
            Column(
                Modifier
                    .align(Alignment.TopCenter)
                    .background(color = Color.White)
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                EventsHeader()
                EventsFields(
                    eventsList = eventsList.toList(),
                    AddTopic = {
                        isAddingOpened.value = !isAddingOpened.value
                    }, firstImage
                )
            }
            Toolbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                selectedItem = 1,
                toolbarDestinations = toolbarDestinations
            )
        }
    } else {
        //                          Тут имя пользователя
        AddEventsScreen(creatorName = "pQuer", onButtonClicked = {
            onEventsAdded(it)
            isAddingOpened.value = !isAddingOpened.value
        }
        )
    }
}


@Composable
fun EventsHeader() {
    Column(
        Modifier
            .padding(40.dp)
    ) {
        Text(
            text = "Мероприятия",
            fontSize = 31.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
    }
}

@Composable
fun EventsFields(eventsList: List<Events>, AddTopic: () -> Unit, image: Painter) {

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        // Create new topic button
        Button(
            onClick = {
                AddTopic()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Создать новое мероприятие")
        }

        Column(Modifier.padding(bottom = 50.dp)) {
            repeat(eventsList.size) {
                ImageWithTitle(item = eventsList[it], painter = image)
            }
        }
    }
}

@Preview
@Composable
fun EventsScreenPreview() {
    EventsScreen(
        toolbarDestinations = listOf({}, {}, {}, {}))
}

@Composable
fun ImageWithTitle( item: Events, painter: Painter) {
    Row(modifier = Modifier
        .padding(10.dp)
        .background(color = White)
        .shadow(2.dp)
    ) {
        Image(
            painter = item.image,
            contentDescription = null,
            modifier = Modifier.size(100.dp, 120.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description,
                style = TextStyle(fontSize = 14.sp)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Text(
                text = item.creatorUsername,
                fontSize = 14.sp,
                fontWeight = FontWeight.W300,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
            )
            Text(
                text = item.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                fontSize = 14.sp,
                fontWeight = FontWeight.W300,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 3.dp, end = 8.dp)
            )
        }
    }
    }
}


@Preview
@Composable
fun ImageWithTitlePreview() {
    val image = painterResource(R.drawable.profile)
    ImageWithTitle(Events(
        name = "Хочу трахнуть собачатину",
        creatorUsername = "Пидор местный",
        description = "Трахать собачекТрахать собачекТрахать собачекТрахать собачекТрахать собачекТрахать собачекТрахать собачек",
        image = image
    ), painter = image
          )
}
