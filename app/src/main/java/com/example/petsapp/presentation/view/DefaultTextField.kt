package com.example.petsapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petsapp.ui.theme.TextFieldColors

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    enteredText: String = "",
    onTextChanged: (String) -> Unit,
    placeholderText: String,
    maxLines: Int = 1,
    readOnly: Boolean = false,
) {

    val text = remember { mutableStateOf(enteredText) }

    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
            onTextChanged(it)
        },
        colors = TextFieldColors,
        textStyle = MaterialTheme.typography.h5,
        readOnly = readOnly,
        maxLines = maxLines,
        placeholder = { MyPlaceholder(placeholderText) },
        modifier = modifier
            .background(Color.White)
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .padding(5.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
    )
}


@Composable
private fun MyPlaceholder(
    placeholderText: String
) {
    Text(text = placeholderText, style = MaterialTheme.typography.h5)
}


@Composable
@Preview
fun AddTaskFieldPreview() {
    DefaultTextField(
        onTextChanged = { onChange(it) },
        placeholderText = "gbjkgbjksd"
    )
}


fun onChange(s: String) = Unit