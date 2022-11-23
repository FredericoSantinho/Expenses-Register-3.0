package neuro.expenses.register.viewmodel.manual.register

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseException
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCase
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModel
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatter
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.main.MainViewModel
import neuro.expenses.register.viewmodel.manual.register.mapper.toViewmodel


class ManualRegisterViewModel(
  private val getCalendarUseCase: GetCalendarUseCase,
  private val observeCategoriesUseCase: ObserveCategoriesUseCase,
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val getNearestPlaceUseCase: GetNearestPlaceUseCase,
  private val feedLastBillViewModel: FeedLastBillViewModel,
  private val decimalFormatter: DecimalFormatter,
  val billViewModel: BillViewModel,
  private val mainViewModel: MainViewModel,
  schedulerProvider: SchedulerProvider,
  private val currency: String
) : BaseViewModel(schedulerProvider) {
  val appBarViewModel: AppBarViewModel = AppBarViewModel()

  val description = mutableStateOf("")
  val category = mutableStateOf("")
  val place = mutableStateOf("")
  val price = mutableStateOf("")
  val amount = mutableStateOf("")
  val total = mutableStateOf(buildTotalStr())

  val calendar = mutableStateOf(getCalendarUseCase.getCalendar())
  val categories = observeCategoriesUseCase.observeCategories()
  val categoriesNames =
    categories.flatMapSingle { Single.just(it).flattenAsObservable { it }.map { it.name }.toList() }

  private val _uiState = mutableStateOf<UiState>(UiState.Ready)
  val uiState = _uiState.asState()
  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  init {
    feedLastBillViewModel.observe().baseSubscribe { }
  }

  fun onNearestPlaceButton() {
    setNearestPlace()
    onPlaceChange()
  }

  fun onRegisterButton() {
    val expenseDto = buildExpense()
    registerExpenseUseCase.registerExpense(expenseDto)
      .baseSubscribe(
        onComplete = { publishAndReset() },
        onError = {
          if (it is RegisterExpenseException) {
            _uiState.value = UiState.Error(it.errors.toViewmodel())
          } else {
            throw it
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
    return decimalFormatter.format(priceDouble * amountDouble) + ' ' + currency
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

  private fun setNearestPlace() {
    getNearestPlaceUseCase.getNearestPlace().baseSubscribe { place.value = it.name }
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

  fun eventConsumed() {
    _uiEvent.value = null
  }

  fun onComposition() {
    mainViewModel.appBarViewModel.value = appBarViewModel
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