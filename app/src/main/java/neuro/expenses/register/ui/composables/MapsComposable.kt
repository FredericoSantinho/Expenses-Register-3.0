package neuro.expenses.register.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.google.maps.android.compose.widgets.ScaleBar

@Composable
fun MapsComposable(cameraPosition: State<LatLng>, height: Dp = 240.dp, zoom: Float = 10f) {
  val bitoque = LatLng(37.091495, -8.2475677)
  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(cameraPosition.value, zoom)
  }
  var mapProperties by remember {
    mutableStateOf(
      MapProperties(mapType = MapType.SATELLITE)
    )
  }

  Box(Modifier.fillMaxWidth()) {
    GoogleMap(
      modifier = Modifier.height(height),
      cameraPositionState = cameraPositionState,
      properties = mapProperties
    ) {
      Marker(
        state = MarkerState(position = bitoque),
        title = "Bitoque",
        snippet = "Marker in Bitoque"
      )
    }
    ScaleBar(
      modifier = Modifier
        .padding(top = 5.dp, end = 15.dp)
        .align(Alignment.TopEnd),
      cameraPositionState = cameraPositionState
    )
  }
}