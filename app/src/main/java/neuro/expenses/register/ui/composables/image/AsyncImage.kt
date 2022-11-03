package neuro.expenses.register.ui.report.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun AsyncImage(modifier: Modifier = Modifier, url: String) {
  Image(
    modifier = modifier,
    painter = rememberAsyncImagePainter(url),
    contentDescription = null,
    contentScale = ContentScale.Crop
  )
}
