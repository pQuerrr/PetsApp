package com.example.petsapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TabsHeader(
    text: String
) {
    Box(Modifier.fillMaxWidth()){
        Text(
            text = text,
            fontSize = 26.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp, bottom = 24.dp, start = 18.dp)
        )
        Box(
            Modifier
                .padding(horizontal = 5.dp, vertical = 5.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
                .align(Alignment.BottomCenter)

        )
    }
}

@Composable
@Preview
fun TabsHeaderPreview() {
    TabsHeader("Предметы")
}