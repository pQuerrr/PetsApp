package com.example.petsapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.petsapp.domain.models.ThemeMessage
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MessageItem(modifier:Modifier = Modifier, item: ThemeMessage, isMainMessage: Boolean = false) {
    ConstraintLayout(
        modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color.White)) {
        
        val (username, text, datetime, divider) = createRefs()
        
        Text(
            text = item.creatorUsername,
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
            modifier = Modifier.constrainAs(username) {
                top.linkTo(parent.top, 4.dp)
                start.linkTo(parent.start, 4.dp)

            })

        Box(
            Modifier
                .height(1.dp)
                .background(Color.LightGray)
                .constrainAs(divider) {
                    start.linkTo(parent.start, 4.dp)
                    end.linkTo(parent.end, 4.dp)
                    top.linkTo(username.bottom, 2.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = item.message,
            fontSize = 16.sp,
            fontWeight = FontWeight.W300,
            maxLines = 20,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(text) {
                    start.linkTo(divider.start, 4.dp)
                    end.linkTo(parent.end, 4.dp)
                    top.linkTo(divider.bottom, 2.dp)
                }
        )

        Text(
            text = item.dateTime.format(DateTimeFormatter.ofPattern("HH:mm, dd.MM.yyyy")),
            fontSize = 14.sp,
            fontWeight = FontWeight.W300,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(datetime) {
                    end.linkTo(parent.end, 4.dp)
                    top.linkTo(text.bottom, 2.dp)
                }
        )
    }
}

@Composable
@Preview
fun MessageItemPreview() {
    MessageItem(
        item = ThemeMessage(
            creatorUsername = "пидорок))",
            dateTime = LocalDateTime.now(),
            message = "Собачку бы под хвостик... Собачку бы под хвостик... Собачку бы под хвостик... Собачку бы под хвостик... Собачку бы под хвостик... Собачку бы под хвостик... Собачку бы под хвостик... Собачку бы под хвостик..."
        )
    )
}