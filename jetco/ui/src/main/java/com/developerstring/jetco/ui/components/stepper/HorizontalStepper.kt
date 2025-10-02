package com.developerstring.jetco.ui.components.stepper

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import com.developerstring.jetco.ui.components.stepper.components.StepperIconSort
import com.developerstring.jetco.ui.components.stepper.model.StepperActionIcons
import com.developerstring.jetco.ui.components.stepper.model.StepperConfig
import com.developerstring.jetco.ui.components.stepper.model.StepperNode
import com.developerstring.jetco.ui.components.stepper.model.StepperStatus

// Horizontal Stepper Component
@Composable
fun HorizontalStepper(
    steps: List<StepperNode>,
    modifier: Modifier = Modifier,
    style: StepperConfig = StepperConfig(),
    stepperActionIcons: StepperActionIcons = StepperActionIcons(),
    showLabels: Boolean = true,
    scrollEnable: Boolean = true,
    onStepClick: ((Int) -> Unit)? = null
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(style.connector.spacing),
        userScrollEnabled = scrollEnable
    ) {
        itemsIndexed(steps) { index, step ->
            HorizontalStepItem(
                step = step,
                isLast = index == steps.lastIndex,
                style = style,
                stepperActionIcons = stepperActionIcons,
                showLabel = showLabels,
                onClick = { onStepClick?.invoke(index) }
            )
        }
    }
}

@Composable
private fun HorizontalStepItem(
    step: StepperNode,
    isLast: Boolean,
    style: StepperConfig,
    stepperActionIcons: StepperActionIcons,
    showLabel: Boolean,
    onClick: () -> Unit
) {
    val nodeColor = when(step.status) {
        StepperStatus.ERROR -> style.node.errorColor
        StepperStatus.COMPLETE -> style.node.completedColor
        StepperStatus.ACTIVE -> style.node.activeColor
        else -> style.node.inactiveColor
    }

    val isActive = step.status == StepperStatus.ACTIVE
    val isComplete = step.status == StepperStatus.COMPLETE

    var animationTriggered by rememberSaveable {
        mutableStateOf(!style.animation.enabled)
    }

    val animatedProgress by animateFloatAsState(
        targetValue = if (animationTriggered) 1f else 0f,
        animationSpec = tween(style.animation.durationMillis),
        label = "node_animation"
    )

    LaunchedEffect(Unit) {
        // Trigger animation on composition
        animationTriggered = true
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(style.node.horizontalStepperWidth)
    ) {
        // Node and connector row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Node
            Card(
                modifier = Modifier
                    .size(style.node.size)
                    .clip(shape = style.node.shape),
                colors = CardDefaults.cardColors(containerColor = nodeColor),
                onClick = onClick
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    StepperIconSort(
                        step = step,
                        stepperActionIcons = stepperActionIcons,
                        isActive = isActive,
                        style = style.node
                    )
                }
            }

            // Connector line (if not last item)
            if (!isLast) {
                Canvas(
                    modifier = Modifier
                        .weight(1f)
                        .height(style.connector.width)
                        .padding(horizontal = style.connector.spacing)
                ) {
                    val strokeColor = when (step.status) {
                        StepperStatus.COMPLETE -> style.node.completedColor
                        StepperStatus.ERROR -> style.node.errorColor
                        else -> style.node.inactiveColor
                    }
                    val pathEffect = if (!isComplete) style.connector.pathEffect else null

                    drawLine(
                        color = strokeColor,
                        start = Offset(0f, size.height / 2),
                        end = Offset(size.width * animatedProgress, size.height / 2),
                        strokeWidth = style.connector.width.toPx(),
                        cap = StrokeCap.Round,
                        pathEffect = pathEffect
                    )
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        // Label
        if (showLabel) {
            step.title?.let {
                Text(
                    text = it,
                    style = style.textConfig.titleTextStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = style.node.spaceBetweenText),
                    maxLines = style.textConfig.maxTitleLines,
                    overflow = style.textConfig.overflow
                )
            }
        }
    }
}