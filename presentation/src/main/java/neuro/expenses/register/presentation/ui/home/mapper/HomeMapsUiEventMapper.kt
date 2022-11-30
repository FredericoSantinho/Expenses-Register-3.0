package neuro.expenses.register.presentation.ui.home.mapper

import neuro.expenses.register.presentation.ui.common.composables.maps.MapsMoveCameraEvent
import neuro.expenses.register.presentation.ui.common.mapper.toPresentation
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
