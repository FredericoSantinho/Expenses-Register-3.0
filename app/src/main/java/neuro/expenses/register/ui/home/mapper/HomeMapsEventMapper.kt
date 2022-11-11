package neuro.expenses.register.ui.home.mapper

import neuro.expenses.register.ui.common.composables.maps.MapsMoveCameraEvent
import neuro.expenses.register.ui.common.mapper.LatLngMapper
import neuro.expenses.register.viewmodel.home.UiEvent

class HomeMapsEventMapper(private val latLngMapper: LatLngMapper) {
  fun map(uiEvent: UiEvent?): MapsMoveCameraEvent? {
    return when (uiEvent) {
      is UiEvent.MoveCamera -> MapsMoveCameraEvent(
        latLngMapper.map(uiEvent.latLngModel),
        uiEvent.zoom
      )
      null -> null
    }
  }
}
