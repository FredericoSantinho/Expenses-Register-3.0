package neuro.expenses.register.viewmodel.manual.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
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
import java.util.*


interface IManualRegisterViewModel {
  val billCardViewModel: IBillCardViewModel
  val description: MutableState<String>
  val category: MutableState<String>
  val place: MutableState<String>
  val price: MutableState<String>
  val amount: MutableState<String>
  val total: MutableState<String>
  val calendar: MutableState<Calendar>
  val categoriesNames: Observable<List<String>>

  val uiState: State<UiState>
  val uiEvent: State<ManualRegisterUiEvent.UiEvent?>

  fun onNearestPlaceButton()
  fun onRegisterButton()
  fun onPriceChange()
  fun onAmountChange()
  fun onDescriptionChange()
  fun onCategoryChange()
  fun onPlaceChange()
  fun eventConsumed()
  fun onComposition()
}

class ManualRegisterViewModel(
  private val getCalendarUseCase: GetCalendarUseCase,
  private val observeCategoriesUseCase: ObserveCategoriesUseCase,
  private val registerExpenseUseCase: RegisterExpenseUseCase,
  private val getNearestPlaceUseCase: GetNearestPlaceUseCase,
  private val currencyFormatter: CurrencyFormatter,
  override val billCardViewModel: IBillCardViewModel,
  private val scaffoldViewModelState: ScaffoldViewModelState,
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider), IManualRegisterViewModel {
  private val appBarViewModel: AppBarViewModel = AppBarViewModel()

  override val description = mutableStateOf("")
  override val category = mutableStateOf("")
  override val place = mutableStateOf("")
  override val price = mutableStateOf("")
  override val amount = mutableStateOf("")
  override val total = mutableStateOf(buildTotalStr())

  override val calendar = mutableStateOf(getCalendarUseCase.getCalendar())
  override val categoriesNames: Observable<List<String>> =
    observeCategoriesUseCase.observeCategories()
      .flatMapSingle { Single.just(it).flattenAsObservable { it }.map { it.name }.toList() }

  private val _uiState = ManualRegisterUiState()
  override val uiState = _uiState.uiState
  private val _uiEvent = ManualRegisterUiEvent()
  override val uiEvent = _uiEvent.uiEvent

  override fun onNearestPlaceButton() {
    setNearestPlace()
    onPlaceChange()
  }

  override fun onRegisterButton() {
    registerExpenseUseCase.registerExpense(buildExpense())
      .baseSubscribe(onComplete = { publishAndReset() }, onError = {
        if (it is RegisterExpenseException) {
          _uiState.error(it.errors.toViewmodel())
        } else {
          throw it
        }
      })
  }

  override fun onPriceChange() {
    total.value = buildTotalStr()
  }

  override fun onAmountChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowInvalidAmountError }

      emitState(errors)
    }

    total.value = buildTotalStr()
  }

  override fun onDescriptionChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowEmptyDescriptionError }

      emitState(errors)
    }
  }

  override fun onCategoryChange() {
    if (uiState.value is UiState.Error) {
      val previousErrors = (uiState.value as UiState.Error).errors
      val errors = previousErrors.filter { it !is UiStateError.ShowCategoryNotExistsError }

      emitState(errors)
    }
  }

  override fun onPlaceChange() {
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

  override fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  override fun onComposition() {
    setupScaffold()
  }

  private fun setupScaffold() {
    appBarViewModel.title.value = ManualRegisterTitle
    scaffoldViewModelState.reset()
    scaffoldViewModelState.appBarViewModel.value = appBarViewModel
  }
}

object ManualRegisterTitle : Title()