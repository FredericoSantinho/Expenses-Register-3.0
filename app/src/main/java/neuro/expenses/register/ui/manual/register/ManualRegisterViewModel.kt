package neuro.expenses.register.ui.manual.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.common.live.data.SingleLiveEvent
import neuro.expenses.register.common.view.model.asLiveData
import neuro.expenses.register.common.view.model.asState
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.category.GetCategoriesUseCase
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCase
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.ui.manual.register.mapper.BillItemMapper
import neuro.expenses.register.ui.manual.register.mapper.RegisterExpenseErrorMapper

class ManualRegisterViewModel(
  private val getCalendarUseCase: GetCalendarUseCase,
  private val getCategoriesUseCase: GetCategoriesUseCase,
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val billItemMapper: BillItemMapper,
  private val registerExpenseErrorMapper: RegisterExpenseErrorMapper,
  private val getNearestPlaceUseCase: GetNearestPlaceUseCase
) : ViewModel() {

  var description = mutableStateOf("")
  var category = mutableStateOf("")
  var place = mutableStateOf("")
  var price = mutableStateOf("")
  var amount = mutableStateOf("")
  var calendar = mutableStateOf(getCalendarUseCase.getCalendar())
  var categories = getCategoriesUseCase.getCategories()

  private val _uiState = mutableStateOf<UiState>(UiState.Ready)
  val uiState = _uiState.asState()
  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent = _uiEvent.asLiveData()

  fun onNearestPlaceButton() {
    place.value = getNearestPlace()
    onPlaceChange()
  }

  fun onRegisterButton() {
    val billItem =
      billItemMapper.map(description.value, category.value, place.value, price.value, amount.value)

    val registerExpenseErrors = registerExpenseUseCase.registerExpense(billItem, calendar.value)

    if (registerExpenseErrors.isEmpty()) {
      publishAndReset()
    } else {
      _uiState.value = UiState.Error(registerExpenseErrorMapper.map(registerExpenseErrors))
    }
  }

  fun onDescriptionChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowDescriptionError }

      emitState(previousErrors, errors)
    }
  }

  fun onCategoryChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowCategoryError }

      emitState(previousErrors, errors)
    }
  }

  fun onPlaceChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowPlaceError }

      emitState(previousErrors, errors)
    }
  }

  private fun publishAndReset() {
    _uiEvent.value = UiEvent.ShowRegisterSuccess(description.value)

    description.value = ""
    category.value = ""
    price.value = ""
    amount.value = ""
  }

  private fun getNearestPlace(): String {
    return getNearestPlaceUseCase.getNearestPlace()
  }

  private fun emitState(
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

sealed class UiEvent {
  class ShowRegisterSuccess(val productName: String) : UiEvent()
}

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