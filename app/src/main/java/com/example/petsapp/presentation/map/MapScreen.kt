package com.example.petsapp.presentation.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.myapplication.presentation.view.common.Toolbar
import com.example.petsapp.R


@Composable
fun MapScreen(
    toolbarDestinations: List<() -> Unit>
) {

    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {

        val (header,
            fields,
            toolbar
        ) = createRefs()



        MapHeader(
            Modifier.constrainAs(header) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }.padding(20.dp)
        )


        MapFields(
            Modifier.fillMaxSize()
                .constrainAs(fields) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(toolbar.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
        )

        Toolbar(
            modifier = Modifier.constrainAs(toolbar) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            selectedItem = 0,
            toolbarDestinations = toolbarDestinations
        )
    }
}


@Composable
fun MapHeader(modifier: Modifier) {
    Column(modifier) {
        Text(
            text = "Полезная карта",
            fontSize = 31.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun MapFields(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.map),
        contentDescription = "Map",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}


@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(toolbarDestinations = listOf({},{},{},{}))

}