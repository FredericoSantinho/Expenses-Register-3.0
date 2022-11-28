package neuro.expenses.register.viewmodel.permissions

import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider


class PermissionsViewModel(
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {

  private val _uiEvent = PermissionsUiEvent()
  val uiEvent = _uiEvent.uiEvent

  init {
    _uiEvent.checkPermissions()
  }

  fun onPermissionsGranted() {
    _uiEvent.navigateToMainActivity()
  }

  fun onPermissionsNotGranted() {
    onPermissionsGranted()
    //    _uiEvent.requestPermissions()
  }

  fun eventConsumed() {
    _uiEvent.eventConsumed()
  }
}
