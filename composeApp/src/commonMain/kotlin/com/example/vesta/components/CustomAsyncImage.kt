package com.example.vesta.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage

@Composable
fun CustomAsyncImage(
    image: String,
    size: Int = 30,
    errorColor: Color = MaterialTheme.colorScheme.background,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillHeight
){
  //  println("получаемая картинка " + image)
    SubcomposeAsyncImage(
        model = image,
        contentDescription = "",
        contentScale = contentScale,
        modifier = modifier
            .size(size.dp),
        loading = { Column(modifier = modifier
            .size(size.dp)
            .background(errorColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary)
            }
        },
        //сделать норм заглушки
        error = { Column(modifier = modifier
            .size(size.dp)
            .background(errorColor)){}
        }
    )
}