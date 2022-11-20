package neuro.expenses.register.viewmodel.permissions

import androidx.lifecycle.MutableLiveData
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.asLiveData
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider


class PermissionsViewModel(
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {

  private val _uiEvent = MutableLiveData<UiEvent?>(null)
  val uiEvent = _uiEvent.asLiveData()

  init {
    _uiEvent.value = UiEvent.CheckPermissions
  }

  fun onPermissionsGranted() {
    _uiEvent.value = UiEvent.NavigateToMainActivity
  }

  fun onPermissionsNotGranted() {
    _uiEvent.value = UiEvent.RequestPermissions
  }

  fun eventConsumed() {
    if (uiEvent.value != null) {
      _uiEvent.value = null
    }
  }
}

sealed class UiEvent {
  object CheckPermissions : UiEvent()
  object RequestPermissions : UiEvent()
  object NavigateToMainActivity : UiEvent()
}