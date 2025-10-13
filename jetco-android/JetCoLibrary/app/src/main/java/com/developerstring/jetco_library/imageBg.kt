package com.developerstring.jetco_library

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Data class to define a crop region
 */
data class CropRegion(
    val offsetX: Int = 0,
    val offsetY: Int = 0,
    val width: Int,
    val height: Int
)


/**
 * Method 4: Custom painter for more control
 */
@Composable
fun ImageWithCustomCrop(
    modifier: Modifier = Modifier,
    resourceId: Int,
    cropRegion: CropRegion,
    contentScale: ContentScale = ContentScale.Fit
) {
    val context = LocalContext.current
    var customPainter by remember { mutableStateOf<BitmapPainter?>(null) }

    LaunchedEffect(resourceId, cropRegion) {
        customPainter = withContext(Dispatchers.IO) {
            try {
                val originalBitmap = BitmapFactory.decodeResource(context.resources, resourceId)

                if (originalBitmap != null) {
                    val croppedBitmap = Bitmap.createBitmap(
                        originalBitmap,
                        cropRegion.offsetX.coerceIn(0, originalBitmap.width),
                        cropRegion.offsetY.coerceIn(0, originalBitmap.height),
                        cropRegion.width.coerceIn(1, originalBitmap.width - cropRegion.offsetX),
                        cropRegion.height.coerceIn(1, originalBitmap.height - cropRegion.offsetY)
                    )

                    BitmapPainter(croppedBitmap.asImageBitmap())
                } else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    customPainter?.let { painter ->
        Image(
            painter = painter,
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}


/**
 * Demo composable showing all methods
 */
@Composable
fun ImageCroppingDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp, top = 30.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Image sprite mapping",
            color = Color.Black,
            fontSize = 18.sp
        )

        // Google
        Card(
            modifier = Modifier.shadow(
                ambientColor = Color.Black,
                spotColor = UIBlue,
                elevation = 20.dp,
                shape = RoundedCornerShape(16.dp),
            ),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            ImageWithCustomCrop(
                modifier = Modifier
                    .size(150.dp),
                resourceId = R.drawable.image_2, // Replace with your image
                cropRegion = CropRegion(
                    offsetX = 0,
                    offsetY = 0,
                    width = (512*2.62).toInt(), // mul by 2.62
                    height = (512*2.62).toInt() // mul by 2.62
                )
            )
        }

        // Jetpack Compose
        Card(
            modifier = Modifier.shadow(
                ambientColor = Color.Green,
                spotColor = Color.Green,
                elevation = 20.dp,
                shape = RoundedCornerShape(16.dp),
            ),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            ImageWithCustomCrop(
                modifier = Modifier
                    .size(150.dp),
                resourceId = R.drawable.image_2, // Replace with your image
                cropRegion = CropRegion(
                    offsetX = (512*2.63).toInt(),
                    offsetY = 0,
                    width = (500*2.62).toInt(), // mul by 2.62
                    height = (541*2.62).toInt() // mul by 2.62
                )
            )
        }

        // Kotlin
        Card(
            modifier = Modifier.shadow(
                ambientColor = Color.Yellow,
                spotColor = Color.Yellow,
                elevation = 20.dp,
                shape = RoundedCornerShape(16.dp),
            ),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            ImageWithCustomCrop(
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .width(275.dp)
                    .height(120.dp),
                resourceId = R.drawable.image_2, // Replace with your image
                cropRegion = CropRegion(
                    offsetX = (1012*2.63).toInt(),
                    offsetY = 0,
                    width = (1299*2.62).toInt(), // mul by 2.62
                    height = (282*2.62).toInt() // mul by 2.62
                )
            )
        }

        // Android
        Card(
            modifier = Modifier.shadow(
                ambientColor = Color.Green,
                spotColor = Color.Green,
                elevation = 20.dp,
                shape = RoundedCornerShape(16.dp),
            ),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            ImageWithCustomCrop(
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .width(275.dp)
                    .height(120.dp),
                resourceId = R.drawable.image_2, // Replace with your image
                cropRegion = CropRegion(
                    offsetX = (1012*2.63).toInt(),
                    offsetY = (352*2.63).toInt(),
                    width = (1563*2.62).toInt(), // mul by 2.62
                    height = (248*2.62).toInt() // mul by 2.62
                )
            )
        }
    }
}
