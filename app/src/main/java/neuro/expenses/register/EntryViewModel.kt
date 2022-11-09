package neuro.expenses.register

import com.exchangebot.common.schedulers.SchedulerProvider
import neuro.expenses.register.common.live.data.SingleLiveEvent
import neuro.expenses.register.common.view.model.BaseViewModel
import neuro.expenses.register.common.view.model.asLiveData


class EntryViewModel(
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {

  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent = _uiEvent.asLiveData()

  init {
    _uiEvent.value = UiEvent.CheckPermissions()
  }

  fun onPermissionsGranted() {
    _uiEvent.value = UiEvent.NavigateToMainActivity()
  }

  fun onPermissionsNotGranted() {
    _uiEvent.value = UiEvent.RequestPermissions()
  }
}

sealed class UiEvent {
  class CheckPermissions : UiEvent()
  class RequestPermissions : UiEvent()
  class NavigateToMainActivity : UiEvent()
}