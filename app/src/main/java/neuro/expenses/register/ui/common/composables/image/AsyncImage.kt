package neuro.expenses.register.ui.report.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import neuro.expenses.register.R
import neuro.expenses.register.common.viewmodel.shimmerBackground

@Composable
fun AsyncImage(
  modifier: Modifier = Modifier, url: String, loading: State<Boolean> = mutableStateOf(false)
) {
  Image(
    modifier = modifier.shimmerBackground(loading.value),
    painter = if (url.isBlank() && !loading.value) painterResource(id = R.drawable.ic_launcher_background) else rememberAsyncImagePainter(
      url
    ),
    contentDescription = null,
    contentScale = ContentScale.Crop
  )
}
