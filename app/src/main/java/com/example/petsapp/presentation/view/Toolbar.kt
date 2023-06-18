package com.example.myapplication.presentation.view.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.petsapp.R

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    selectedItem: Int,
    toolbarDestinations: List<() -> Unit>
) {

    val resList = listOf(
        Pair(R.drawable.baseline_map_24, "Карта"),
        Pair(R.drawable.baseline_forum_24, "Форум"),
        Pair(R.drawable.baseline_event_24, "Мероприятия"),
        Pair(R.drawable.baseline_person_24, "Профиль"),
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Color.White)
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(4) { index ->
            ToolbarItem(
                isSelected = index == selectedItem,
                iconRes = resList[index].first,
                textRes = resList[index].second,
                onClick = toolbarDestinations[index]
            )
        }
    }
}

@Composable
fun ToolbarItem(
    isSelected: Boolean,
    iconRes: Int,
    textRes: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = if (isSelected) Color.Blue else Color.Gray,
            modifier = Modifier
                .size(25.dp)
        )
        Text(
            text = textRes,
            color = if (isSelected) Color.Blue else Color.Gray,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            lineHeight = 20.sp,
        )
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    Toolbar(Modifier, 0, toolbarDestinations = listOf({}, {}, {}, {}))
}