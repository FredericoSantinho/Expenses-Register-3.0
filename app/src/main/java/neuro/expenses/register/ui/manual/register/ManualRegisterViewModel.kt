package neuro.expenses.register.ui.manual.register

import androidx.compose.runtime.mutableStateOf
import com.exchangebot.common.schedulers.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import neuro.expenses.register.common.live.data.SingleLiveEvent
import neuro.expenses.register.common.view.model.BaseViewModel
import neuro.expenses.register.common.view.model.asLiveData
import neuro.expenses.register.common.view.model.asState
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCase
import neuro.expenses.register.domain.usecase.register.RegisterExpenseUseCase
import neuro.expenses.register.ui.common.bill.FeedLastBillViewModel
import neuro.expenses.register.ui.common.formatter.DoubleFormatter
import neuro.expenses.register.ui.home.view.model.BillViewModel
import neuro.expenses.register.ui.manual.register.mapper.RegisterExpenseErrorMapper


class ManualRegisterViewModel(
  private val getCalendarUseCase: GetCalendarUseCase,
  private val observeCategoriesUseCase: ObserveCategoriesUseCase,
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val getNearestPlaceUseCase: GetNearestPlaceUseCase,
  private val feedLastBillViewModel: FeedLastBillViewModel,
  private val registerExpenseErrorMapper: RegisterExpenseErrorMapper,
  private val schedulerProvider: SchedulerProvider,
  private val doubleFormatter: DoubleFormatter,
  val billViewModel: BillViewModel
) : BaseViewModel(schedulerProvider) {

  val description = mutableStateOf("")
  val category = mutableStateOf("")
  val place = mutableStateOf("")
  val price = mutableStateOf("")
  val amount = mutableStateOf("")
  val total = mutableStateOf(buildTotalStr())

  val calendar = mutableStateOf(getCalendarUseCase.getCalendar())
  val categories = observeCategoriesUseCase.observeCategories()
  private val disposable: CompositeDisposable = CompositeDisposable()
  private val _uiState = mutableStateOf<UiState>(UiState.Ready)

  val uiState = _uiState.asState()
  private val _uiEvent = SingleLiveEvent<UiEvent>()
  val uiEvent = _uiEvent.asLiveData()

  init {
    disposable.add(feedLastBillViewModel.subscribe())
  }

  override fun onCleared() {
    super.onCleared()

    disposable.clear()
  }

  fun onNearestPlaceButton() {
    place.value = getNearestPlace()
    onPlaceChange()
  }

  fun onRegisterButton() {
    disposable.add(
      registerExpenseUseCase.registerExpense(buildExpense())
        .baseSubscribe { registerExpenseErrors ->
          if (registerExpenseErrors.isEmpty()) {
            publishAndReset()
          } else {
            _uiState.value = UiState.Error(registerExpenseErrorMapper.map(registerExpenseErrors))
          }
        })
  }

  fun onPriceChange() {
    total.value = buildTotalStr()
  }

  fun onAmountChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowAmountError }

      emitState(previousErrors, errors)
    }

    total.value = buildTotalStr()
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

  private fun buildTotalStr(): String {
    val priceDouble = if (price.value.isBlank()) 0.0 else price.value.toDouble()
    val amountDouble = if (amount.value.isBlank()) 0.0 else amount.value.toDouble()
    return doubleFormatter.format(priceDouble * amountDouble)
  }

  private fun buildExpense(): ExpenseDto {
    return ExpenseDto(
      description.value,
      category.value,
      place.value,
      if (price.value.isEmpty()) 0.0 else price.value.toDouble(),
      if (amount.value.isEmpty()) 0.0 else amount.value.toDouble(),
      calendar.value
    )
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
  class ShowRegisterSuccess(val productDescription: String) : UiEvent()
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