package neuro.expenses.register.viewmodel.manual.register

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseException
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.place.GetNearestPlaceUseCase
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.appbar.Title
import neuro.expenses.register.viewmodel.bill.IBillCardViewModel
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatter
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.UiState
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.UiStateError
import neuro.expenses.register.viewmodel.manual.register.mapper.toViewmodel
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState


class ManualRegisterViewModel(
  private val getCalendarUseCase: GetCalendarUseCase,
  private val observeCategoriesUseCase: ObserveCategoriesUseCase,
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val getNearestPlaceUseCase: GetNearestPlaceUseCase,
  private val currencyFormatter: CurrencyFormatter,
  val billCardViewModel: IBillCardViewModel,
  private val scaffoldViewModelState: ScaffoldViewModelState,
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {
  private val appBarViewModel: AppBarViewModel = AppBarViewModel()

  val description = mutableStateOf("")
  val category = mutableStateOf("")
  val place = mutableStateOf("")
  val price = mutableStateOf("")
  val amount = mutableStateOf("")
  val total = mutableStateOf(buildTotalStr())

  val calendar = mutableStateOf(getCalendarUseCase.getCalendar())
  val categoriesNames: Observable<List<String>> = observeCategoriesUseCase.observeCategories()
    .flatMapSingle { Single.just(it).flattenAsObservable { it }.map { it.name }.toList() }

  private val _uiState = ManualRegisterUiState()
  val uiState = _uiState.uiState
  private val _uiEvent = ManualRegisterUiEvent()
  val uiEvent = _uiEvent.uiEvent

  fun onNearestPlaceButton() {
    setNearestPlace()
    onPlaceChange()
  }

  fun onRegisterButton() {
    registerExpenseUseCase.registerExpense(buildExpense())
      .baseSubscribe(onComplete = { publishAndReset() }, onError = {
        if (it is RegisterExpenseException) {
          _uiState.error(it.errors.toViewmodel())
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
      val errors = previousErrors.filter { it !is UiStateError.ShowInvalidAmountError }

      emitState(errors)
    }

    total.value = buildTotalStr()
  }

  fun onDescriptionChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowEmptyDescriptionError }

      emitState(errors)
    }
  }

  fun onCategoryChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowCategoryNotExistsError }

      emitState(errors)
    }
  }

  fun onPlaceChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowEmptyPlaceError }

      emitState(errors)
    }
  }

  private fun buildTotalStr(): String {
    val priceDouble = if (price.value.isBlank()) 0.0 else price.value.toDouble()
    val amountDouble = if (amount.value.isBlank()) 0.0 else amount.value.toDouble()
    return currencyFormatter.format(priceDouble * amountDouble)
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
    _uiEvent.showRegisterSuccess(description.value)

    description.value = ""
    category.value = ""
    price.value = ""
    amount.value = ""
  }

  private fun setNearestPlace() {
    getNearestPlaceUseCase.getNearestPlace().baseSubscribe { place.value = it.name }
  }

  private fun emitState(errors: List<UiStateError>) {
    if (errors.isEmpty()) {
      _uiState.ready()
    } else {
      _uiState.error(errors)
    }
  }

  fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  fun onComposition() {
    setupScaffold()
  }

  private fun setupScaffold() {
    appBarViewModel.title.value = ManualRegisterTitle
    scaffoldViewModelState.reset()
    scaffoldViewModelState.appBarViewModel.value = appBarViewModel
  }
}

object ManualRegisterTitle : Title()