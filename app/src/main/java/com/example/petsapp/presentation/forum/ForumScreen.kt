package com.example.petsapp.presentation.forum

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.view.common.Toolbar
import com.example.petsapp.domain.models.Theme
import com.example.petsapp.presentation.view.DefaultButton
import com.example.petsapp.presentation.view.DefaultTextField
import com.example.petsapp.presentation.view.TabsHeader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun ForumScreen(
    onReadThemeClicked: () -> Unit,
    toolbarDestinations: List<() -> Unit>
) {

    val isAddingOpened = remember { mutableStateOf(false) }

    val themesList = remember {
        mutableStateListOf(
            //Тут какие темы будут
            Theme(
                name = "Мой пес стал более агрессивным",
                creatorUsername = "MarkProvorov",
                dateTime = LocalDateTime.of(2023, 5, 19,10,10,10),
                description = "Совсем недавно мой пес стал более агрессивным, с чем это может быть связано?"
            ),
            Theme(
                name = "Мой кот самый лучший, попробуйте доказать обратное",
                creatorUsername = "MaxBozjenko",
                dateTime = LocalDateTime.of(2023, 5, 20,10,10,10),
                description = "Мой кот лучший! Попробуй переубеди."
            ),
            Theme(
                name = "Можно ли дать собаке лук?",
                creatorUsername = "Yulia",
                dateTime = LocalDateTime.of(2023, 6, 15,10,10,10),
                description = "Заметила как моя соседка дает лук своей собаке. Подскажите можно ли вообще собакам лук?"
            )
        )
    }

    fun onThemeAdded(newTheme: Theme) {
        themesList.add(newTheme)
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
                ForumHeader()
                ForumFields(
                    themesList = themesList.toList(),
                    AddTopic = {
                        isAddingOpened.value = !isAddingOpened.value
                               },
                    onReadThemeClicked = onReadThemeClicked
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
        AddThemeScreen(creatorName = "Хуесосик", onButtonClicked = {
            onThemeAdded(it)
            isAddingOpened.value = !isAddingOpened.value
        }
        )
    }
}


@Composable
fun ForumHeader() {
    Column(
        Modifier
            .padding(40.dp)
    ) {
        Text(
            text = "Форум",
            fontSize = 31.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
    }
}

@Composable
fun ForumFields(themesList: List<Theme>, AddTopic: () -> Unit, onReadThemeClicked: () -> Unit) {

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        // Display forum topics
        Text(
            text = "Темы форума",
            fontSize = 31.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
        // Create new topic button
        Button(
            onClick = {
                AddTopic()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Создать новую тему")
        }

        Column(Modifier.padding(bottom = 50.dp)) {
            repeat(themesList.size) {
                ThemeItem(item = themesList[it]) { onReadThemeClicked() }
            }
        }
    }
}

@Preview
@Composable
fun ForumScreenPreview() {
    ForumScreen(
        toolbarDestinations = listOf({}, {}, {}, {}),
        onReadThemeClicked = {})
}

@Composable
fun ThemeItem(
    item: Theme,
    onReadClicked: () -> Unit
) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(150.dp)
            .background(Color.White)
            .shadow(2.dp, RoundedCornerShape(8.dp))

    ) {
        Text(
            text = item.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth()
                .padding(start = 12.dp, top = 8.dp)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(1.dp)
                .background(Color.LightGray)
        )
        Text(
            text = item.description,
            fontSize = 18.sp,
            fontWeight = FontWeight.W300,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth()
                .padding(start = 12.dp, top = 3.dp)
        )
        Row(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(start = 12.dp, top = 10.dp)
        ) {

            Text(
                text = item.creatorUsername,
                fontSize = 14.sp,
                fontWeight = FontWeight.W300,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(0.3F)
                    .align(Alignment.CenterVertically)
                    .padding(start = 3.dp)
            )
            Text(
                text = item.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                fontSize = 14.sp,
                fontWeight = FontWeight.W300,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(0.3F)
                    .align(Alignment.CenterVertically)
                    .padding(start = 3.dp)
            )
            DefaultButton(
                modifier = Modifier
                    .weight(0.3F)
                    .height(30.dp)
                    .padding(end = 12.dp),
                buttonText = "Читать",
                onButtonClicked = { onReadClicked() }
            )

        }
    }
}

@Composable
@Preview
fun ThemeItemPreview() {
    ThemeItem(Theme(
        name = "Хочу трахнуть собачатину",
        creatorUsername = "Пидор местный",
        description = "Трахать собачекТрахать собачекТрахать собачекТрахать собачекТрахать собачекТрахать собачекТрахать собачек"
    ), {})
}