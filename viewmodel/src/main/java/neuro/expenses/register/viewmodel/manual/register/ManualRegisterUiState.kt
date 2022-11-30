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

  sealed class UiStateError(val message: Message) {
    class ShowDescriptionError(message: Message) : UiStateError(message)
    class ShowPlaceError(message: Message) : UiStateError(message)
    class ShowAmountError(message: Message) : UiStateError(message)
    class ShowCategoryError(message: Message) : UiStateError(message)
  }

  enum class Message {
    EMPTY_DESCRIPTION,
    CATEGORY_DOES_NOT_EXIST,
    EMPTY_PLACE,
    INVALID_AMOUNT
  }
}