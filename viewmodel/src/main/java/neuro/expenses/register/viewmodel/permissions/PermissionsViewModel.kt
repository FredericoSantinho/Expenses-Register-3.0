package neuro.expenses.register.viewmodel.permissions

import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.asLiveData
import neuro.expenses.register.viewmodel.common.livedata.SingleLiveEvent
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider


class PermissionsViewModel(
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {

  private val _uiEvent = SingleLiveEvent<UiEvent>()
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
}

sealed class UiEvent {
  object CheckPermissions : UiEvent()
  object RequestPermissions : UiEvent()
  object NavigateToMainActivity : UiEvent()
}