package neuro.expenses.register.ui.report.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import neuro.expenses.register.R

@Composable
fun AsyncImage(modifier: Modifier = Modifier, url: String) {
  Image(
    modifier = modifier,
    painter = if (url.isBlank()) painterResource(id = R.drawable.ic_launcher_background) else rememberAsyncImagePainter(
      url
    ),
    contentDescription = null,
    contentScale = ContentScale.Crop
  )
}
