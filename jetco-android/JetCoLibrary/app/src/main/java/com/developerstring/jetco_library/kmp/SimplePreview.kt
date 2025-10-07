package com.developerstring.jetco_library.kmp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developerstring.jetco.ui.charts.piechart.PieChart
import com.developerstring.jetco.ui.charts.piechart.config.PieChartDefaults

@Composable
fun SimplePreview() {

    val chartData = mapOf(
        Pair("Test-1", 50f),
        Pair("Test-2", 80f),
        Pair("Test-3.beta", 30f),
        Pair("Test-4", 90f),
        Pair("Test-5", 45f),
        Pair("Test-6.beta", 30f),
        Pair("Test-7", 90f),
        Pair("Test-8", 45f),
        Pair("Test-9", 50f),
        Pair("Test-10", 80f),
        Pair("Test-11", 50f),
        Pair("Test-12", 80f),
    )

    Column(modifier = Modifier.fillMaxSize().padding(top = 50.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        PieChart(
            modifier = Modifier.fillMaxWidth(),
            chartData = chartData,
            pieChartConfig = PieChartDefaults.pieChartConfig(
                radius = 90.dp,
                isChartItemScrollEnable = false
            ),
            pieChartAnimationConfig = PieChartDefaults.pieChartAnimationConfig(
                animationDuration = 2000
            )
        )
    }

}