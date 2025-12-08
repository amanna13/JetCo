package com.developerstring.jetco_kmp.preview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.developerstring.jetco_kmp.components.search.animated_searchbar.AnimatedSearchBar
import com.developerstring.jetco_kmp.components.search.animated_searchbar.rememberAnimatedSearchBarController
import kotlinx.coroutines.launch

@Composable
fun AnimatedSearchBarPreview() {
    var query by remember { mutableStateOf("") }
    val controller = rememberAnimatedSearchBarController()
    var results by remember { mutableStateOf<List<String>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            AnimatedSearchBar(
                value = query,
                onValueChange = { query = it },
                controller = controller,
                isLoading = isLoading,
                onSearch = { q ->
                    isLoading = true
                    scope.launch {
                        // tiny delay to simulate request
                        kotlinx.coroutines.delay(1200)
                        // produce fake results
                        results = if (q.isBlank()) emptyList()
                        else List(6) { index -> "${q.trim()} result ${index + 1}" }
                        isLoading = false
                    }
                },
                onExpand = { /* optional: handle expand */ },
                onCollapse = { /* optional: handle collapse */ }

            )

            Spacer(modifier = Modifier.height(16.dp))

            //Simulate fake results for preview
            if (results.isNotEmpty()) {
                Text(
                    "Results",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(4.dp)
                )

                HorizontalDivider()

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(results) { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth().clickable {
                            // When user clicks a result:
                            // 1) set the query text
                            // 2) collapse the search bar via controller
                            query = item
                            controller.collapse()
                            // optional: clear results if you want them hidden
                            results = emptyList()
                        }.padding(vertical = 12.dp, horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(item, modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null
                            )
                        }
                        HorizontalDivider()
                    }
                }
            } else {
                // optional empty-state hint
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        "Type and press search to simulate results",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
