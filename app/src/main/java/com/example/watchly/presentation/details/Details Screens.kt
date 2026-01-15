package com.example.watchly.presentation.details

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.watchly.presentation.shimmer.ShimmerDetails
import com.example.watchly.data.model.TitleDetailsResponse

@Composable
fun MovieDetailsScreen(
    id: Int,
    viewModel: DetailsViewModel,
    navController: NavHostController
) {
    val details by viewModel.details.collectAsState()
    val isLoading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()
    val context = LocalContext.current



    LaunchedEffect(id) {
        viewModel.loadDetails(id)
    }

    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, "Error: $it", Toast.LENGTH_LONG).show()
            navController.popBackStack()
        }
    }

    when {
        isLoading -> {
            ShimmerDetails()
        }

        details != null -> {
            MovieDetailsContent(
                details = details!!,
                onBackClick = { navController.popBackStack() }
            )
        }

    }
}

@Composable
fun MovieDetailsContent(
    details: TitleDetailsResponse,
    onBackClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            AsyncImage(
                model = details.backdrop ?: details.poster,
                contentDescription = "Movie backdrop",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {


            Spacer(modifier = Modifier.height(300.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 500.dp),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(24.dp)
                ) {
                    Text(
                        text = details.title?.takeIf { it.isNotBlank() }
                            ?: details.original_title?.takeIf { it.isNotBlank() }
                            ?: "Unknown Title",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        lineHeight = 36.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        details.release_date?.let { releaseDate ->
                            Text(
                                text = releaseDate,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )

                            details.runtime_minutes?.let { runtime ->
                                Text(
                                    text = " • ",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )

                                val hours = runtime / 60
                                val minutes = runtime % 60
                                Text(
                                    text = "${hours}h ${minutes}m",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    details.genre_names?.let { genres ->
                        if (genres.isNotEmpty()) {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(genres) { genre ->
                                    GenreChip(genre)
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = details.user_rating ?: "N/A",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFA000)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    details.plot_overview?.let { plot ->
                        Text(
                            text = plot,
                            fontSize = 15.sp,
                            lineHeight = 22.sp,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                }
            }
        }

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFF424242), CircleShape)
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}

@Composable
fun GenreChip(genre: String) {
    Surface(
        color = Color(0xFFF5F5F5),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0))
    ) {
        Text(
            text = genre,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
