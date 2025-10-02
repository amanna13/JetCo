package com.developerstring.jetco.ui.cards.ticket

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Corner configuration for TicketCard.
 */
@Keep
@Stable
data class TicketCardCorner(
    val topLeft: Dp = 25.dp,
    val topRight: Dp = 25.dp,
    val bottomRight: Dp = 25.dp,
    val bottomLeft: Dp = 25.dp
)