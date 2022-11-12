package neuro.expenses.register.ui.home.mapper

import neuro.expenses.register.ui.common.composables.maps.MapsMoveCameraEvent
import neuro.expenses.register.ui.common.mapper.LatLngViewMapper
import neuro.expenses.register.viewmodel.home.UiEvent

class HomeMapsEventMapper(private val latLngViewMapper: LatLngViewMapper) {
  fun map(uiEvent: UiEvent?): MapsMoveCameraEvent? {
    return when (uiEvent) {
      is UiEvent.MoveCamera -> MapsMoveCameraEvent(
        latLngViewMapper.map(uiEvent.latLngModel),
        uiEvent.zoom
      )
      else -> null
    }
  }
}
