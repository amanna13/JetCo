package com.developerstring.jetco_kmp.cards.curved

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Stable

/**
 * Configuration class for CurvedCard appearance and behavior.
 *
 * @param waveHeight Height of the wave curves in Dp. Default is 10.dp.
 * @param waveSegments Number of wave segments. If null, calculated automatically based on width.
 * @param shape Shape of the card container. Default is RoundedCornerShape(20.dp).
 * @param gradient Brush gradient for the background when no image is provided.
 * @param samplesPerWave Number of sample points per wave segment for smoothness. Default is 20.
 * @param topCurveEnable Whether to enable the top curved wave. Default is true.
 * @param bottomCurveEnable Whether to enable the bottom curved wave. Default is false.
 * @param image Optional background image as ImageBitmap.
 * @param imageAlpha Alpha transparency for the background image. Range 0.0f to 1.0f. Default is 1.0f.
 * @param contentPadding Padding applied to the content inside the card.
 */
@Stable
data class CurvedCardConfig(
    val waveHeight: Dp = 10.dp,
    val waveSegments: Int? = null,
    val shape: Shape = RoundedCornerShape(20.dp),
    val gradient: Brush = Brush.linearGradient(
        colors = listOf(Color(0xFF8EC8FF), Color(0xFFFFC77A))
    ),
    val samplesPerWave: Int = 20,
    val topCurveEnable: Boolean = true,
    val bottomCurveEnable: Boolean = false,
    val image: ImageBitmap? = null,
    val imageAlpha: Float = 1.0f,
    val contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
)