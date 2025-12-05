package com.developerstring.jetco.ui.components.search.animated_searchbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * Creates and remembers an instance of [AnimatedSearchBarController] for use with
 * [AnimatedSearchBar].
 *
 * The controller allows the parent UI to trigger collapse behavior externally,
 * enabling scenarios where:
 * - the user selects a search result,
 * - a navigation event occurs,
 * - or the screen must reset the search bar programmatically.
 *
 * ## Usage Example
 * ```kotlin
 * val controller = rememberAnimatedSearchBarController()
 * ```
**/

@Composable
fun rememberAnimatedSearchBarController(): AnimatedSearchBarController {
    return remember { AnimatedSearchBarController() }
}
