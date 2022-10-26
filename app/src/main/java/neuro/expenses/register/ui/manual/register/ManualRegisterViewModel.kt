package neuro.expenses.register.ui.manual.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import neuro.expenses.register.common.live.data.SingleLiveEvent
import neuro.expenses.register.ui.manual.register.composable.datetime.Date
import neuro.expenses.register.ui.manual.register.composable.datetime.Time

class ManualRegisterViewModel : ViewModel() {

  var description = mutableStateOf("")
  var category = mutableStateOf("")
  var place = mutableStateOf("")
  var price = mutableStateOf("")
  var amount = mutableStateOf("")
  var categories = mutableStateOf(getCategories())

  private val _uiState = mutableStateOf<UiState>(UiState.Ready)
  val uiState: State<UiState> = _uiState
  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent: LiveData<UiEvent> = _uiEvent

  private fun getCategories(): List<String> {
    return listOf(
      "aaa",
      "abb",
      "abc"
    )
  }

  fun onNearestPlaceButton() {
    place.value = getNearestPlace()
    onPlaceChange()
  }

  fun onRegisterButton(time: Time, date: Date) {
    register(time, date)
  }

  private fun register(
    time: Time,
    date: Date
  ) {
  }

  private fun getNearestPlace(): String {
    return "teste"
  }

  fun onDescriptionChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowDescriptionError }

      emitStateIfNeeded(previousErrors, errors)
    }
  }

  fun onCategoryChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowCategoryError }

      emitStateIfNeeded(previousErrors, errors)
    }
  }

  fun onPlaceChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowPlaceError }

      emitStateIfNeeded(previousErrors, errors)
    }
  }

  private fun emitStateIfNeeded(
    previousErrors: List<UiStateError>,
    errors: List<UiStateError>
  ) {
    if (errors.isEmpty()) {
      _uiState.value = UiState.Ready
    } else {
      if (previousErrors.size != errors.size) {
        _uiState.value = UiState.Error(errors)
      }
    }
  }
}

sealed class UiEvent

sealed class UiState {
  object Ready : UiState()
  data class Error(val errors: List<UiStateError>) : UiState()
}

sealed class UiStateError {
  data class ShowDescriptionError(val message: Message) : UiStateError()
  data class ShowPlaceError(val message: Message) : UiStateError()
  object ShowCategoryError : UiStateError()
}

enum class Message {
  EMPTY_DESCRIPTION,
  EMPTY_PLACE
}