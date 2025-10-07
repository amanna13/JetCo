package com.developerstring.jetco_library.kmp

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.developerstring.jetco.ui.components.button.switch_button.SwitchButton
import com.developerstring.jetco.ui.components.button.switch_button.SwitchButtonAnimation
import com.developerstring.jetco.ui.components.button.switch_button.SwitchButtonConfig
import com.developerstring.jetco.ui.components.button.switch_button.SwitchButtonIcon

@Composable
fun SwitchButtonKMP() {

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
            buttonSizeHeight = 110.dp,
            buttonSizeWidth = 200.dp,
            isSelected = isSelected,
            animation = SwitchButtonAnimation(
                animationDuration = 900
            ),
            switchButtonConfig = SwitchButtonConfig(
                switchPadding = 6.dp
            ),
            icon = {
                SwitchButtonIcon(
                    iconModifier = Modifier.size(50.dp),
                    isSelected = isSelected,
                    selectedIcon = Icons.Rounded.Check,
                    unSelectedIcon = Icons.Rounded.Close,
                    enableRotate = true,
                    rotationDuration = 800,
                )
            },
            onStateChange = { isSelected = it }
        )

    }

}