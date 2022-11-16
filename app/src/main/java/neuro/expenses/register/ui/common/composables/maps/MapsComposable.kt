package neuro.expenses.register.ui.common.composables.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch
import neuro.expenses.register.ui.common.composables.google.ScaleBar
import neuro.expenses.register.ui.common.composables.maps.mapper.toPresentation
import neuro.expenses.register.viewmodel.home.model.CameraPositionModel

@Composable
fun MapsComposable(
  initialCameraPosition: CameraPositionModel,
  event: MapsMoveCameraEvent?,
  height: Dp = 240.dp
) {
  val mapLoaded = remember { mutableStateOf(false) }
  val bitoque = LatLng(37.091495, -8.2475677)
  val cameraPositionState = rememberCameraPositionState {
    position = initialCameraPosition.toPresentation()
  }
  val mapProperties by remember {
    mutableStateOf(
      MapProperties(mapType = MapType.SATELLITE)
    )
  }
  val coroutineScope = rememberCoroutineScope()

  Box(Modifier.fillMaxWidth()) {
    GoogleMap(
      modifier = Modifier.height(height),
      cameraPositionState = cameraPositionState,
      properties = mapProperties,
      onMapLoaded = {
        mapLoaded.value = true
      }
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

  val mapsMoveCameraEvent = event
  if (mapLoaded.value && mapsMoveCameraEvent != null) {
    LaunchedEffect(mapsMoveCameraEvent) {
      coroutineScope.launch {
        cameraPositionState.animate(
          CameraUpdateFactory.newCameraPosition(
            CameraPosition.fromLatLngZoom(
              mapsMoveCameraEvent.latLng, mapsMoveCameraEvent.zoom
            )
          )
        )
      }
    }
  }
}