package com.developerstring.jetco.ui.cards.curved

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlin.math.PI
import kotlin.math.sin

/**
 * A professional curved card component with customizable wave animations and styling.
 *
 * This composable creates a card with curved/wavy edges that can be animated. The card supports
 * both gradient backgrounds and image backgrounds with configurable transparency. Wave animations
 * can be applied to both top and bottom edges independently with customizable direction and timing.
 *
 * ## Features:
 * - Customizable wave height and segments
 * - Independent top and bottom wave animations
 * - Support for both gradient and image backgrounds
 * - Configurable animation timing and direction
 * - Professional configuration through data classes
 * - Optimized animations using Compose's infinite transition
 *
 * ## Usage Example:
 * ```kotlin
 * CurvedCard(
 *     modifier = Modifier.fillMaxWidth().height(200.dp),
 *     config = CurvedCardConfig(
 *         waveHeight = 15.dp,
 *         topCurveEnable = true,
 *         bottomCurveEnable = true,
 *         image = myImageBitmap,
 *         imageAlpha = 0.8f
 *     ),
 *     animConfig = CurvedCardAnimConfig(
 *         animateTopWave = true,
 *         animateBottomWave = true,
 *         animationDurationMs = 3000,
 *         reverseAnimationTop = true
 *     )
 * ) {
 *     Text(
 *         text = "Your content here",
 *         color = Color.White,
 *         modifier = Modifier.align(Alignment.Center)
 *     )
 * }
 * ```
 *
 * @param modifier Modifier to be applied to the card container.
 * @param config Configuration object containing all appearance and behavior settings.
 * @param animConfig Configuration object containing all animation-related settings.
 * @param content Composable content to be displayed inside the card.
 *
 * @see CurvedCardConfig for appearance configuration options
 * @see CurvedCardAnimConfig for animation configuration options
 */
@Composable
fun CurvedCard(
    modifier: Modifier = Modifier,
    config: CurvedCardConfig = CurvedCardConfig(),
    animConfig: CurvedCardAnimConfig = CurvedCardAnimConfig(),
    content: @Composable () -> Unit = {}
) {
    val density = LocalDensity.current

    Box(
        modifier = modifier
            .clip(config.shape)
    ) {
        // Use Compose's built-in infinite transition for optimal performance
        val infiniteTransition = rememberInfiniteTransition(label = "wave_animation")
        val phaseShiftAnim by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 2f * PI.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = animConfig.animationDurationMs,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            ),
            label = "phase_shift"
        )

        // Draw wave + gradient background clipped to the wave path
        Canvas(modifier = Modifier.matchParentSize()) {
            val w = size.width
            val h = size.height

            val waveSeg: Int = config.waveSegments ?: (w / 120).toInt().coerceAtLeast(1)
            val waveH = with(density) { config.waveHeight.toPx() }

            // Build path with wavy curves
            val path = Path().apply {
                val totalSteps = (waveSeg * config.samplesPerWave).coerceAtLeast(1)
                val stepX = w / totalSteps
                val amplitude = waveH * 0.6f
                val baseline = waveH

                // Start at left edge
                moveTo(0f, if (!config.topCurveEnable) 0f else baseline)

                // Draw top wave if enabled
                if (config.topCurveEnable) {
                    for (s in 0..totalSteps) {
                        val x = s * stepX
                        val angle = ((x / w) * waveSeg * 2f * PI.toFloat()) +
                            if (animConfig.animateTopWave) {
                                if (animConfig.reverseAnimationTop) -phaseShiftAnim else phaseShiftAnim
                            } else 0f
                        val y = baseline + amplitude * sin(angle)
                        lineTo(x, y)
                    }
                }

                // Handle top edge when only bottom curve is enabled
                if (!config.topCurveEnable && config.bottomCurveEnable) {
                    lineTo(w, 0f)
                }

                // Draw bottom wave if enabled
                if (config.bottomCurveEnable) {
                    lineTo(w, h - baseline)
                    for (s in totalSteps downTo 0) {
                        val x = s * stepX
                        val angle = ((x / w) * waveSeg * 2f * PI.toFloat()) +
                            if (animConfig.animateBottomWave) {
                                if (animConfig.reverseAnimationBottom) -phaseShiftAnim else phaseShiftAnim
                            } else 0f
                        val y = (h - baseline) + amplitude * sin(angle)
                        lineTo(x, y)
                    }
                } else if (config.topCurveEnable) {
                    // Simple rectangle bottom
                    lineTo(w, h)
                    lineTo(0f, h)
                }
                close()
            }

            // Clip to path and draw background
            clipPath(path) {
                config.image?.let { imageBitmap ->
                    drawImage(
                        image = imageBitmap,
                        dstSize = IntSize(w.toInt(), h.toInt()),
                        dstOffset = IntOffset(0, 0),
                        style = Fill,
                        alpha = config.imageAlpha
                    )
                } ?: run {
                    drawRect(
                        brush = config.gradient,
                        size = Size(w, h),
                        style = Fill
                    )
                }
            }
        }

        // Content container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(config.contentPadding),
        ) {
            content()
        }
    }
}