package com.developerstring.jetco_kmp.components.search.animated_searchbar

import androidx.compose.runtime.Stable

/**
 * Controller used to interact with [AnimatedSearchBar] from outside the component.
 *
 * A controller enables external UI elements (such as search result lists, navigation
 * actions, or parent screens) to programmatically collapse the search bar without
 * requiring direct state lifting.
 *
 * ## Usage Example
 * ```kotlin
 * val controller = rememberAnimatedSearchBarController()
 *
 * AnimatedSearchBar(
 *     value = query,
 *     onValueChange = { query = it },
 *     controller = controller
 * )
 *
 * // Collapse from outside (e.g., when a result is clicked)
 * controller.collapse()
 * ```
 * Internally, [AnimatedSearchBar] registers a collapse request callback so the controller
 * always triggers the same close animation and logic as the built-in clear button.
 **/

@Stable
class AnimatedSearchBarController {
    internal var collapseRequest: (() -> Unit)? = null

    fun collapse() {
        collapseRequest?.invoke()
    }
}