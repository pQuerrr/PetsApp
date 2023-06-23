package com.example.petsapp.ui.theme

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

object TextFieldColors: TextFieldColors {

    @Composable
    override fun backgroundColor(enabled: Boolean) = rememberUpdatedState(Color.White)

    @Composable
    override fun cursorColor(isError: Boolean) = rememberUpdatedState(Color.Black)

    @Composable
    override fun indicatorColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource
    ) = rememberUpdatedState(Color.Unspecified)

    @Composable
    override fun labelColor(
        enabled: Boolean,
        error: Boolean,
        interactionSource: InteractionSource
    ) = rememberUpdatedState(Color.LightGray)

    @Composable
    override fun leadingIconColor(enabled: Boolean, isError: Boolean) = rememberUpdatedState(Color.Black)

    @Composable
    override fun placeholderColor(enabled: Boolean) = rememberUpdatedState(Color.Black)

    @Composable
    override fun textColor(enabled: Boolean) = rememberUpdatedState(Color.Black)

    @Composable
    override fun trailingIconColor(enabled: Boolean, isError: Boolean) = rememberUpdatedState(Color.Black)

}