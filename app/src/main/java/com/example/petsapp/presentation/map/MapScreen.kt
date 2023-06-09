package com.example.petsapp.presentation.map

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Map() {
    MapHeader()
    MapFields()
    MapFooter()
}

@Composable
fun MapHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Полезная карта", fontSize = 31.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun MapFields() {
    navBar()
}

@Composable
fun MapFooter() {

}



@Composable
fun navBar() {


}