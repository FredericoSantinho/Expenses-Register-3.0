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
    class ShowEmptyDescriptionError() : UiStateError(Message.EMPTY_DESCRIPTION)
    class ShowCategoryNotExistsError() : UiStateError(Message.CATEGORY_DOES_NOT_EXIST)
    class ShowEmptyPlaceError() : UiStateError(Message.EMPTY_PLACE)
    class ShowInvalidAmountError() : UiStateError(Message.INVALID_AMOUNT)

    override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (other !is UiStateError) return false

      return message == other.message
    }

    override fun hashCode(): Int {
      return message.hashCode()
    }
  }

  enum class Message {
    EMPTY_DESCRIPTION,
    CATEGORY_DOES_NOT_EXIST,
    EMPTY_PLACE,
    INVALID_AMOUNT
  }
}