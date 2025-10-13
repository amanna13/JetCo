package com.developerstring.jetco_kmp.cards.curved

import androidx.compose.runtime.Stable
/**
 * Configuration class for CurvedCard wave animation parameters.
 *
 * @param animateTopWave Whether to animate the top wave. Default is false.
 * @param animateBottomWave Whether to animate the bottom wave. Default is false.
 * @param animationDurationMs Duration of one complete animation cycle in milliseconds. Default is 2500ms.
 * @param reverseAnimationTop Whether to reverse the top wave animation direction. Default is false.
 * @param reverseAnimationBottom Whether to reverse the bottom wave animation direction. Default is false.
 */
@Stable
data class CurvedCardAnimConfig(
    val animateTopWave: Boolean = false,
    val animateBottomWave: Boolean = false,
    val animationDurationMs: Int = 2500,
    val reverseAnimationTop: Boolean = false,
    val reverseAnimationBottom: Boolean = false
)