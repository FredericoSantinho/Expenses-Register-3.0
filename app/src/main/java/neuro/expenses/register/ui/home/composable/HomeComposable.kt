package neuro.expenses.register.ui.home.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.android.gms.maps.model.LatLng

@Composable
fun HomeComposable() {
  val latLng = remember { mutableStateOf(LatLng(37.091495, -8.2475677)) }

  MapsComposable(latLng, zoom = 19.0f)
}