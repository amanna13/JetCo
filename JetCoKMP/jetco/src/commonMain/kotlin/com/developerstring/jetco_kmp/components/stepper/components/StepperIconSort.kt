package com.developerstring.jetco_kmp.components.stepper.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developerstring.jetco_kmp.components.stepper.model.StepperActionIcons
import com.developerstring.jetco_kmp.components.stepper.model.StepperConfig
import com.developerstring.jetco_kmp.components.stepper.model.StepperNode
import com.developerstring.jetco_kmp.components.stepper.model.StepperStatus

@Composable
fun StepperIconSort(
    step: StepperNode,
    stepperActionIcons: StepperActionIcons,
    style: StepperConfig.NodeStyle,
    isActive: Boolean,
) {
    when {
        step.status == StepperStatus.ERROR -> {
            Icon(
                imageVector = stepperActionIcons.error,
                contentDescription = "Error",
                tint = style.actionIconColor,
                modifier = Modifier.size(16.dp)
            )
        }
        step.status == StepperStatus.COMPLETE -> {
            Icon(
                imageVector = stepperActionIcons.completed,
                contentDescription = "Completed",
                tint = style.actionIconColor,
                modifier = Modifier.size(16.dp)
            )
        }
        step.icon != null -> {
            Icon(
                imageVector = step.icon,
                contentDescription = step.title,
                tint = if (isActive) style.actionIconColor else style.idleIconColor,
                modifier = Modifier.size(16.dp)
            )
        }
        else -> {
            Icon(
                imageVector = stepperActionIcons.active,
                contentDescription = step.title,
                tint = if (isActive) style.actionIconColor else style.idleIconColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}