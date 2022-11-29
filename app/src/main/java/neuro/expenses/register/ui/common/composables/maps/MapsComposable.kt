package neuro.expenses.register.ui.common.composables.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch
import neuro.expenses.register.ui.common.composables.maps.mapper.toPresentation
import neuro.expenses.register.ui.common.composables.maps.mapper.toViewModel
import neuro.expenses.register.viewmodel.home.model.CameraPositionModel

@Composable
fun MapsComposable(
  cameraPosition: MutableState<CameraPositionModel>,
  moveCamera: MutableState<MapsMoveCameraEvent?> = mutableStateOf(null),
  modifier: Modifier = Modifier
) {
  val mapLoaded = remember { mutableStateOf(false) }
  val bitoque = LatLng(37.091495, -8.2475677)
  val cameraPositionState = rememberCameraPositionState {
    position = cameraPosition.value.toPresentation()
  }
  val mapProperties by remember {
    mutableStateOf(
      MapProperties(mapType = MapType.SATELLITE)
    )
  }
  val coroutineScope = rememberCoroutineScope()

  Box(Modifier.fillMaxWidth()) {
    GoogleMap(modifier = modifier,
      cameraPositionState = cameraPositionState,
      properties = mapProperties,
      onMapLoaded = {
        mapLoaded.value = true
      }) {
      Marker(
        state = MarkerState(position = bitoque), title = "Bitoque", snippet = "Marker in Bitoque"
      )
    }
    ScaleBar(
      modifier = Modifier
        .padding(top = 5.dp, end = 15.dp)
        .align(Alignment.TopEnd),
      cameraPositionState = cameraPositionState
    )
  }

  cameraPosition.value = cameraPositionState.position.toViewModel()

  moveCamera.value?.let {
    LaunchedEffect(it) {
      coroutineScope.launch {
        cameraPositionState.animate(
          CameraUpdateFactory.newCameraPosition(
            CameraPosition.fromLatLngZoom(
              it.latLng, it.zoom
            )
          )
        )
      }
    }
  }
}