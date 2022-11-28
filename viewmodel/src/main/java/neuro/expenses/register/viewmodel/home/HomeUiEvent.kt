package neuro.expenses.register.viewmodel.home

import neuro.expenses.register.viewmodel.common.BaseUiEvent
import neuro.expenses.register.viewmodel.home.HomeUiEvent.UiEvent
import neuro.expenses.register.viewmodel.home.model.LatLngModel

class HomeUiEvent : BaseUiEvent<UiEvent>() {
  fun openEditPlaceProduct() {
    _uiEvent.value = UiEvent.OpenEditMode()
  }

  fun moveCamera(latLngModel: LatLngModel, zoom: Float) {
    _uiEvent.value = UiEvent.MoveCamera(latLngModel, zoom)
  }

  fun closeEditPlaceProduct() {
    _uiEvent.value = UiEvent.CloseEditMode()
  }

  fun requestLocationPermission() {
    _uiEvent.value = UiEvent.RequestLocationPermission()
  }

  sealed class UiEvent {
    class MoveCamera(val latLngModel: LatLngModel, val zoom: Float) : UiEvent()
    class OpenEditMode : UiEvent()
    class CloseEditMode : UiEvent()
    class RequestLocationPermission : UiEvent()
  }
}