package neuro.expenses.register.ui.home.mapper

import neuro.expenses.register.ui.common.composables.maps.MapsMoveCameraEvent
import neuro.expenses.register.ui.common.mapper.toPresentation
import neuro.expenses.register.viewmodel.home.HomeUiEvent.UiEvent

class HomeMapsUiEventMapper() {
  fun map(uiEvent: UiEvent?): MapsMoveCameraEvent? {
    return when (uiEvent) {
      is UiEvent.MoveCamera -> MapsMoveCameraEvent(
        uiEvent.latLngModel.toPresentation(),
        uiEvent.zoom
      )
      else -> null
    }
  }
}
