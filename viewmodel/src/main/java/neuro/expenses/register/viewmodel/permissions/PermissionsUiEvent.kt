package neuro.expenses.register.viewmodel.permissions

import androidx.lifecycle.MutableLiveData
import neuro.expenses.register.viewmodel.common.asLiveData

class PermissionsUiEvent {
  private val _uiEvent = MutableLiveData<UiEvent?>(null)
  val uiEvent = _uiEvent.asLiveData()

  fun eventConsumed() {
    uiEvent.value?.let { _uiEvent.value = null }
  }

  fun checkPermissions() {
    _uiEvent.value = UiEvent.CheckPermissions
  }

  fun navigateToMainActivity() {
    _uiEvent.value = UiEvent.NavigateToMainActivity
  }

  fun requestPermissions() {
    _uiEvent.value = UiEvent.RequestPermissions
  }

  sealed class UiEvent {
    object CheckPermissions : UiEvent()
    object RequestPermissions : UiEvent()
    object NavigateToMainActivity : UiEvent()
  }
}