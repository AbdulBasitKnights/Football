package com.football.league.ui.utils

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val gradientBackground = Modifier.background(
    brush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFEFB8C8),
            Color(0xFF6650a4)
        )
    )
)