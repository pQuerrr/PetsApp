package com.example.petsapp.presentation.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.view.common.Toolbar
import javax.annotation.Untainted


@Composable
fun EventsScreen(
    toolbarDestinations: List<() -> Unit>,
    AddEventsClick: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .background(color = Color.Black)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            EventsHeader()
            EventsFields(AddEventsClick = AddEventsClick)
            EventsFooter()
        }
        Toolbar(
            modifier = Modifier.align(Alignment.BottomCenter),
            selectedItem = 2,
            toolbarDestinations = toolbarDestinations
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
            color = Color.White
        )
    }
}

@Composable
fun EventsFields(AddEventsClick: () -> Unit) {
    Column(
    modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
) {
    // Display forum topics
    // Create new topic button
    Button(
        onClick =  AddEventsClick ,
        modifier = Modifier.align(Alignment.End)
    ) {
        Text(text = "Создать новое мероприятие")
    }
}

}

@Composable
fun EventsFooter() {

}

@Preview
@Composable
fun EventsScreenPreview() {
    EventsScreen(toolbarDestinations = listOf({},{},{},{}),{})
}
