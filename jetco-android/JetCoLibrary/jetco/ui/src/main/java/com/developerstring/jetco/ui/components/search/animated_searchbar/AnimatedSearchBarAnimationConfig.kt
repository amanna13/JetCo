package com.developerstring.jetco.ui.components.search.animated_searchbar

import androidx.compose.animation.core.Spring

/**
 * Animation configuration class for [AnimatedSearchBar].
 *
 * Controls spring physics, animation timings, and overshoot values for both the
 * width expansion and the scale "pop" effect.
 *
 * @param rotationDuration Duration (in milliseconds) of the search-icon rotation
 *                         that occurs during expansion.
 *                         Default is **500ms**.
 *
 * @param bounceStiffness Spring stiffness applied to the icon bounce animation.
 *                        Lower stiffness = softer bounce, higher stiffness = snappier.
 *                        Default is **Spring.StiffnessMediumLow**.
 *
 * @param bounceDamping Spring damping ratio for bounce animation.
 *                      Higher damping reduces oscillation.
 *                      Default is **Spring.DampingRatioMediumBouncy**.
 *
 * @param widthSpringStiffness Spring stiffness for width expansion/collapse animation.
 *                             Controls how fast the bar grows/shrinks.
 *                             Default is **Spring.StiffnessLow**.
 *
 * @param widthSpringDamping Damping ratio applied during width animation.
 *                           Controls how much overshoot or bounce occurs.
 *                           Default is **Spring.DampingRatioLowBouncy**.
 *
 * @param fadeDuration Duration (in milliseconds) for fade-in / fade-out transitions
 *                     used when showing or hiding content inside the expanded bar.
 *                     Default is **200ms**.
 *
 * @see AnimatedSearchBarConfig for appearance configuration options
 */

data class AnimatedSearchBarAnimationConfig(
    val rotationDuration: Int = 500,
    val bounceStiffness: Float = Spring.StiffnessMediumLow,
    val bounceDamping: Float = Spring.DampingRatioMediumBouncy,

    val widthSpringStiffness: Float = Spring.StiffnessLow,
    val widthSpringDamping: Float = Spring.DampingRatioLowBouncy,

    val fadeDuration: Int = 200,
)
