package com.developerstring.jetco_library

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.developerstring.jetco.ui.components.button.switch_button.SwitchButton
import com.developerstring.jetco.ui.components.button.switch_button.SwitchButtonAnimation
import com.developerstring.jetco.ui.components.button.switch_button.SwitchButtonConfig
import com.developerstring.jetco.ui.components.button.switch_button.SwitchButtonIcon

@Composable
fun SwitchButtonPreview() {

    var isSelected by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF16212B) else Color(0xFFF4F6F0),
        animationSpec = tween(
            durationMillis = 900, // adjust speed
            easing = FastOutSlowInEasing // smooth curve
        ), label = "backgroundColorAnim"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = backgroundColor
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        SwitchButton(
            buttonSizeHeight = 55.dp,
            buttonSizeWidth = 100.dp,
            isSelected = isSelected,
            animation = SwitchButtonAnimation(
                animationDuration = 900
            ),
            switchButtonConfig = SwitchButtonConfig(
                switchPadding = 6.dp
            ),
            icon = {
                SwitchButtonIcon(
                    isSelected = isSelected,
                    selectedIcon = ImageVector.vectorResource(R.drawable.rounded_partly_cloudy_night_24),
                    unSelectedIcon = ImageVector.vectorResource(R.drawable.rounded_partly_cloudy_day_24),
                    enableRotate = true,
                    rotationDuration = 800,
                )
            },
            onStateChange = { isSelected = it }
        )

    }

}