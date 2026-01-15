package com.example.watchly.presentation.shimmer

import androidx.compose.animation.core.*
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.watchly.ui.theme.app.shimmerColors


fun Modifier.shimmerEffect(): Modifier = composed {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translate"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    this.background(brush)
}

@Composable
fun HomeDataShimmer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) { Box(
            modifier = Modifier
                .width(90.dp)
                .height(130.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()
            )
        }
    }
}


@Composable
fun ShimmerDetails() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Backdrop / Poster
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Title
            Box(
                modifier = Modifier
                    .height(28.dp)
                    .width(220.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .shimmerEffect()
            )

            // Release date
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .shimmerEffect()
            )

            // Paragraph lines
            repeat(4) {
                Box(
                    modifier = Modifier
                        .height(14.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .shimmerEffect()
                )
            }
        }
    }
}

