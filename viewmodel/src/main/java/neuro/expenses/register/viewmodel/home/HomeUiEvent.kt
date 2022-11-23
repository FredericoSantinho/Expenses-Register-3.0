package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.home.model.LatLngModel

class HomeUiEvent {
  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  fun eventConsumed() {
    _uiEvent.value = null
  }

  fun openEditPlaceProduct() {
    _uiEvent.value = UiEvent.OpenEditMode()
  }

  fun moveCamera(latLngModel: LatLngModel, zoom: Float) {
    _uiEvent.value = UiEvent.MoveCamera(latLngModel, zoom)
  }

  fun closeEditPlaceProduct() {
    _uiEvent.value = UiEvent.CloseEditMode()
  }

  sealed class UiEvent {
    class MoveCamera(val latLngModel: LatLngModel, val zoom: Float) : UiEvent()
    class OpenEditMode : UiEvent()
    class CloseEditMode : UiEvent()
  }
}