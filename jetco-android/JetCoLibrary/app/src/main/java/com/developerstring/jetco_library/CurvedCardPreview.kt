package com.developerstring.jetco_library

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developerstring.jetco.ui.cards.curved.CurvedCard
import com.developerstring.jetco.ui.cards.curved.CurvedCardConfig
import com.developerstring.jetco.ui.cards.curved.CurvedCardAnimConfig
import kotlinx.coroutines.delay

/**
 * Preview composable demonstrating various CurvedCard configurations and animations.
 * Shows examples of gradient backgrounds, image backgrounds, and animated text content.
 */
@Composable
fun CurvedCardPreview() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF03122A)).padding(top = 50.dp, bottom = 30.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(120.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(20.dp),
                    ambientColor = Color.White,
                    spotColor = Color.White
                ),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0E0)),
            shape = RoundedCornerShape(20.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "🎉 This is a example of Curved Card",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFA85200)
                    )
                }

                CurvedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    config = CurvedCardConfig(
                        shape = RoundedCornerShape(20.dp),
                        gradient = Brush.horizontalGradient(
                            listOf(
                                Color(0xFF8EC8FF),
                                Color(0xFFFFC77A)
                            )
                        )
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "You saved ₹31 with Gold",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.White,
                spotColor = Color.White
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                CurvedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp),
                    config = CurvedCardConfig(
                        image = ImageBitmap.imageResource(R.drawable.image_2),
                        shape = RoundedCornerShape(20.dp),
                        bottomCurveEnable = true,
                        topCurveEnable = false
                    ),
                    animConfig = CurvedCardAnimConfig(
                        animateBottomWave = true,
                        reverseAnimationBottom = true,
                        animationDurationMs = 2500
                    )
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Image background with bottom wave",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF2561FF),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "A breathtaking, tranquil mountain landscape reflected perfectly in a still body of water. Towering, snow-capped peaks dominate the background, with soft layers of mist creating a sense of depth and serenity.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray,
                )
                Spacer(modifier = Modifier.height(15.dp))
            }

        }


        CurvedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            config = CurvedCardConfig(
                image = ImageBitmap.imageResource(R.drawable.image_2),
                shape = RoundedCornerShape(0.dp),
                bottomCurveEnable = true,
                topCurveEnable = true,
                imageAlpha = 0.7f,
                waveSegments = 4,
                waveHeight = 15.dp
            ),
            animConfig = CurvedCardAnimConfig(
                animateTopWave = true,
                animateBottomWave = true,
                reverseAnimationBottom = false,
                reverseAnimationTop = true,
                animationDurationMs = 2000
            )
        ) {
            val textList = listOf(
                "Hello Developers! 🎉",
                "Did you loved JetCo Library? ❤️",
                "Give a star on GitHub! ⭐",
                "Follow me on GitHub! 🥳",
                "Happy Coding! 🚀"
            )

            // State to track current text index
            var currentTextIndex by remember { mutableIntStateOf(0) }

            // Auto-advance text every 2 seconds
            LaunchedEffect(textList.size) {
                while (true) {
                    delay(2000) // 2 seconds delay
                    currentTextIndex = (currentTextIndex + 1) % textList.size
                }
            }

            // Animated text content
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(
                    targetState = currentTextIndex,
                    transitionSpec = {
                        ContentTransform(
                            targetContentEnter = slideInVertically(
                                animationSpec = tween(500),
                                initialOffsetY = { it }
                            ),
                            initialContentExit = slideOutVertically(
                                animationSpec = tween(500),
                                targetOffsetY = { -it }
                            )
                        )
                    },
                    label = "text_animation"
                ) { index ->
                    Text(
                        text = textList[index],
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

        }
    }
}
