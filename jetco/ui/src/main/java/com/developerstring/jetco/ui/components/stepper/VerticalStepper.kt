package com.developerstring.jetco.ui.components.stepper

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.developerstring.jetco.ui.components.stepper.components.StepperIconSort
import com.developerstring.jetco.ui.components.stepper.model.StepperActionIcons
import com.developerstring.jetco.ui.components.stepper.model.StepperConfig
import com.developerstring.jetco.ui.components.stepper.model.StepperNode
import com.developerstring.jetco.ui.components.stepper.model.StepperStatus

// Vertical Stepper Component
@Composable
fun VerticalStepper(
    steps: List<StepperNode>,
    modifier: Modifier = Modifier,
    style: StepperConfig = StepperConfig(),
    stepperActionIcons: StepperActionIcons = StepperActionIcons(),
    scrollEnable: Boolean = true,
    onStepClick: ((Int) -> Unit)? = null
) {

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

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(style.connector.spacing),
        userScrollEnabled = scrollEnable,
    ) {
        itemsIndexed(steps) { index, step ->
            VerticalStepItem(
                step = step,
                isLast = index == steps.lastIndex,
                style = style,
                stepperActionIcons = stepperActionIcons,
                animatedProgress = animatedProgress,
                onClick = { onStepClick?.invoke(index) }
            )
        }
    }
}

@Composable
private fun VerticalStepItem(
    step: StepperNode,
    isLast: Boolean,
    style: StepperConfig,
    stepperActionIcons: StepperActionIcons,
    animatedProgress: Float,
    onClick: () -> Unit
) {
    val nodeColor = when (step.status) {
        StepperStatus.ERROR -> style.node.errorColor
        StepperStatus.COMPLETE -> style.node.completedColor
        StepperStatus.ACTIVE -> style.node.activeColor
        else -> style.node.inactiveColor
    }

    val isActive = step.status == StepperStatus.ACTIVE
    val isComplete = step.status == StepperStatus.COMPLETE

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.Top
    ) {
        // Node and connector column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(style.node.size)
                .fillMaxHeight()
        ) {
            // Node
            Card(
                modifier = Modifier
                    .size(style.node.size)
                    .clip(shape = style.node.shape),
                colors = CardDefaults.cardColors(containerColor = nodeColor),
                onClick = onClick,
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
                        .width(style.connector.width)
                        .heightIn(min = style.connector.lineLengthMin)
                        .weight(1f)
                        .padding(top = 4.dp)
                ) {
                    val strokeColor = when (step.status) {
                        StepperStatus.COMPLETE -> style.node.completedColor
                        StepperStatus.ERROR -> style.node.errorColor
                        else -> style.node.inactiveColor
                    }
                    val pathEffect = if (!isComplete) style.connector.pathEffect else null

                    drawLine(
                        color = strokeColor,
                        start = Offset(size.width / 2, 0f),
                        end = Offset(size.width / 2, size.height * animatedProgress),
                        strokeWidth = style.connector.width.toPx(),
                        cap = StrokeCap.Round,
                        pathEffect = pathEffect
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(style.node.internalSpacing))

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            step.painter?.let { painter ->
                Image(
                    modifier = Modifier
                        .padding(style.imageConfig.paddingValues)
                        .then(
                            if (style.imageConfig.maxWidth != null && style.imageConfig.maxHeight != null) {
                                Modifier.size(
                                    style.imageConfig.maxWidth,
                                    style.imageConfig.maxHeight
                                ) // Force fixed size
                            } else if (style.imageConfig.maxWidth != null) {
                                Modifier
                                    .width(style.imageConfig.maxWidth)
                                    .fillMaxHeight()
                            } else if (style.imageConfig.maxHeight != null) {
                                Modifier
                                    .height(style.imageConfig.maxHeight)
                                    .fillMaxWidth()
                            } else {
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f) // Default flexible
                            }
                        )
                        .clip(shape = style.imageConfig.imageShape),
                    painter = painter,
                    contentDescription = step.title,
                )

            }

            step.title?.let {
                Text(
                    text = it,
                    fontWeight =
                        if (style.textConfig.titleTextStyle.fontWeight != null)
                            style.textConfig.titleTextStyle.fontWeight
                        else if (isActive) FontWeight.Bold else FontWeight.Normal,
                    style = style.textConfig.titleTextStyle,
                    maxLines = style.textConfig.maxTitleLines,
                    overflow = style.textConfig.overflow
                )
            }



            step.description?.let { description ->
                Text(
                    text = description,
                    style = style.textConfig.descriptionTextStyle,
                    modifier = Modifier.padding(top = style.node.spaceBetweenText),
                    maxLines = style.textConfig.maxDescriptionLines,
                    overflow = style.textConfig.overflow
                )
            }
        }
    }
}