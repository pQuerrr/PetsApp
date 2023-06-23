package com.example.petsapp.presentation.forumMessages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.petsapp.R
import com.example.petsapp.domain.models.Theme
import com.example.petsapp.domain.models.ThemeMessage
import com.example.petsapp.presentation.view.DefaultButton
import com.example.petsapp.presentation.view.DefaultTextField
import com.example.petsapp.presentation.view.MessageItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ForumMessagesScreen(
    onBackPressed: () -> Unit
) {

    val initialTheme = remember {
        mutableStateOf(
            Theme(
                dateTime = LocalDateTime.of(2023, 6, 15,10,10,10),
                name = "Можно ли дать собаке лук?",
                creatorUsername = "Yulia",
                description = "Заметила как моя соседка дает лук своей собаке. Подскажите можно ли вообще собакам лук?)"
            )
        )
    }

    val messages = remember {
        mutableStateListOf(
            ThemeMessage(
                creatorUsername = "pQuer",
                dateTime = LocalDateTime.of(2023, 6, 15,14,10,10),
                message = "Нет конечно! Предупредите ее, что лук плохо влияет на нюх собак, а также может вызвать диарею."
            ), ThemeMessage(
                creatorUsername = "markProvorov",
                dateTime = LocalDateTime.of(2023, 6, 15,16,44,10),
                message = "Какой ужас...."
            )
        )
    }

    val isHeaderExpanded = remember { mutableStateOf(false) }

    val newMessageItem = remember { mutableStateOf(ThemeMessage()) }

    fun onNewMessageAdded(message: ThemeMessage) {
        messages.add(message)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 16.dp)
                        .clickable { onBackPressed() },
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null
                )

                Box(
                    modifier = Modifier
                        .padding(end = 24.dp, top = 8.dp)
                        .size(45.dp)
                        .clip(RoundedCornerShape(50))
                        .border(2.dp, Color.LightGray, RoundedCornerShape(30.dp))
                        .clickable { isHeaderExpanded.value = !isHeaderExpanded.value }

                ) {
                    Text(
                        text = "+",
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W300,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
            if (isHeaderExpanded.value) {
                DefaultTextField(
                    Modifier
                        .padding(vertical = 10.dp, horizontal = 8.dp),
                    onTextChanged = {
                        newMessageItem.value = newMessageItem.value.copy(message = it)
                    },
                    placeholderText = "Введите текст сообщения",
                    maxLines = 7
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(horizontal = 30.dp, vertical = 8.dp),
                    onButtonClicked = {
                        onNewMessageAdded(
                            newMessageItem.value.copy(
                                dateTime = LocalDateTime.now(),
                                creatorUsername = "Пидористик"
                            )
                        )
                        isHeaderExpanded.value = !isHeaderExpanded.value
                    },
                    buttonText = "Отправить сообщение"
                )
            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = initialTheme.value.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.W500,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 8.dp)
            )
            ConstraintLayout(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(Color.White)
            ) {

                val (username, text, datetime) = createRefs()

                Text(
                    text = initialTheme.value.creatorUsername,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier.constrainAs(username) {
                        top.linkTo(text.bottom, 3.dp)
                        start.linkTo(parent.start, 4.dp)

                    })

                Text(
                    text = initialTheme.value.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W300,
                    maxLines = 20,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(text) {
                            start.linkTo(parent.start, 21.dp)
                            end.linkTo(parent.end, 4.dp)
                            top.linkTo(parent.top, 2.dp)
                        }
                )

                Text(
                    text = initialTheme.value.dateTime.format(DateTimeFormatter.ofPattern("HH:mm, dd.MM.yyyy")),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W300,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .constrainAs(datetime) {
                            end.linkTo(parent.end, 4.dp)
                            top.linkTo(text.bottom, 3.dp)
                        }
                )
            }
        }
        repeat(messages.size) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Black)
            )
            MessageItem(item = messages[it])
        }
    }
}


@Preview
@Composable
fun ForumMessagesScreen() {
    ForumMessagesScreen({})
}