package com.example.petsapp.presentation.forum

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


@Composable
fun ForumScreen(
    toolbarDestinations: List<() -> Unit>,
    AddTopic: () -> Unit
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
            ForumHeader()
            ForumFields(AddTopic = AddTopic)
            ForumFooter()
        }
        Toolbar(
            modifier = Modifier.align(Alignment.BottomCenter),
            selectedItem = 1,
            toolbarDestinations = toolbarDestinations
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
            color = Color.White
        )
    }
}

@Composable
fun ForumFields(AddTopic: () -> Unit) {
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
            color = Color.White
        )
        // Create new topic button
        Button(
            onClick = AddTopic,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Создать новую тему")
        }
    }
}


@Composable
fun ForumFooter() {

}

@Preview
@Composable
fun ForumScreenPreview() {
    ForumScreen(toolbarDestinations = listOf({}, {}, {}, {}),{})
}