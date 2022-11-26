package neuro.expenses.register.viewmodel.manual.register

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.asState

class ManualRegisterUiState {
  private val _uiState = mutableStateOf<UiState>(UiState.Ready)
  val uiState = _uiState.asState()

  fun error(errors: List<UiStateError>) {
    _uiState.value = UiState.Error(errors)
  }

  fun ready() {
    _uiState.value = UiState.Ready
  }

  sealed class UiState {
    object Ready : UiState()
    data class Error(val errors: List<UiStateError>) : UiState()
  }

  sealed class UiStateError {
    data class ShowDescriptionError(val message: Message) : UiStateError()
    data class ShowPlaceError(val message: Message) : UiStateError()
    data class ShowAmountError(val message: Message) : UiStateError()
    object ShowCategoryError : UiStateError()
  }

  enum class Message {
    EMPTY_DESCRIPTION,
    EMPTY_PLACE,
    INVALID_AMOUNT
  }
}