package com.developerstring.jetco_kmp.cards.ticket

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A customizable Ticket-style card with optional cut-out notches and a dashed divider.
 *
 * @param modifier Modifier for styling and layout.
 * @param cornerRadius Defines corner radii for each corner of the card.
 * @param notchRadius Radius of the side notches.
 * @param cardColor Solid background color of the card (ignored if [cardBrush] is provided).
 * @param cardBrush Optional gradient/brush background.
 * @param dividerEffect PathEffect for the dashed divider.
 * @param dividerStrokeWidth Width of the dashed divider line.
 * @param dividerColor Color of the dashed divider line.
 * @param notchWeight Vertical position of the notches as a fraction of card height (0f to 1f).
 * @param content Card body composable content. Use [TicketContent] to split the card into sections.
 */
@Composable
fun TicketCard(
    modifier: Modifier = Modifier,
    cornerRadius: TicketCardCorner = TicketCardCorner(),
    notchRadius: Dp = 25.dp,
    cardColor: Color = Color.White,
    cardBrush: Brush? = null,
    dividerEffect: PathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f),
    dividerStrokeWidth: Float = 5f,
    dividerColor: Color = Color.Gray,
    notchWeight: Float = 0.7f,
    content: @Composable () -> Unit = {}
) {
    val density = LocalDensity.current

    val topLeft = with(density) { cornerRadius.topLeft.toPx() }
    val topRight = with(density) { cornerRadius.topRight.toPx() }
    val bottomRight = with(density) { cornerRadius.bottomRight.toPx() }
    val bottomLeft = with(density) { cornerRadius.bottomLeft.toPx() }
    val notch = with(density) { notchRadius.toPx() }

    Box(
        modifier = modifier.drawBehind {
            // Base rounded rectangle
            val rectPath = Path().apply {
                addRoundRect(
                    RoundRect(
                        left = 0f,
                        top = 0f,
                        right = size.width,
                        bottom = size.height,
                        topLeftCornerRadius = CornerRadius(topLeft, topLeft),
                        topRightCornerRadius = CornerRadius(topRight, topRight),
                        bottomRightCornerRadius = CornerRadius(bottomRight, bottomRight),
                        bottomLeftCornerRadius = CornerRadius(bottomLeft, bottomLeft)
                    )
                )
            }

            // Notch cutouts
            val notchPath = Path().apply {
                addArc(
                    oval = Rect(Offset(0f, size.height * notchWeight), notch),
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = 180f
                )
                addArc(
                    oval = Rect(Offset(size.width, size.height * notchWeight), notch),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 180f
                )
            }

            // Subtract notches
            val finalPath = Path.combine(PathOperation.Difference, rectPath, notchPath)

            // Draw background
            if (cardBrush != null) {
                drawPath(path = finalPath, brush = cardBrush)
            } else {
                drawPath(path = finalPath, color = cardColor)
            }

            // Divider line between sections
            drawLine(
                color = dividerColor,
                start = Offset(notch, size.height * notchWeight),
                end = Offset(size.width - notch, size.height * notchWeight),
                pathEffect = dividerEffect,
                strokeWidth = dividerStrokeWidth
            )
        }
    ) {
        content()
    }
}

