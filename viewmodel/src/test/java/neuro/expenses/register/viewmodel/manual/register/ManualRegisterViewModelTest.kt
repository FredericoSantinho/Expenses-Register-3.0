package neuro.expenses.register.viewmodel.manual.register

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.usecase.calendar.GetCalendarUseCase
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseException
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCase
import neuro.expenses.register.domain.usecase.near.GetNearestPlaceUseCase
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.bill.IBillCardViewModel
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatter
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiEvent.UiEvent
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.UiState
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.UiStateError
import neuro.expenses.register.viewmodel.mock.categoryDtoMock
import neuro.expenses.register.viewmodel.mock.expenseMockDto
import neuro.expenses.register.viewmodel.mock.placeDtoMock
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState
import neuro.expenses.register.viewmodel.test.TestSchedulerProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.*

internal class ManualRegisterViewModelTest {

  @Test
  fun onComposition() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.just(emptyList()))

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    whenever(scaffoldViewModelState.appBarViewModel).thenReturn(mutableStateOf(AppBarViewModel()))

    verifyNoInteractions(scaffoldViewModelState)

    manualRegisterViewModel.onComposition()

    verify(scaffoldViewModelState, times(1)).reset()
    assertEquals(ManualRegisterTitle, scaffoldViewModelState.appBarViewModel.value.title.value)
  }


  @Test
  fun registerExpenseSuccess() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    val calendar = Calendar.getInstance()
    whenever(getCalendarUseCase.getCalendar()).thenReturn(Calendar.getInstance())
    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.just(emptyList()))

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    val description = "description"
    val category = "category"
    val place = "place"
    val priceDouble = 1.0
    val price = priceDouble.toString()
    val amountDouble = 1.0
    val amount = amountDouble.toString()
    val expenseDto = expenseMockDto(
      description = description,
      category = category,
      place = place,
      price = priceDouble,
      amount = amountDouble,
      calendar = calendar
    )

    whenever(registerExpenseUseCase.registerExpense(expenseDto)).thenReturn(Completable.complete())

    manualRegisterViewModel.description.value = description
    manualRegisterViewModel.category.value = category
    manualRegisterViewModel.place.value = place
    manualRegisterViewModel.price.value = price
    manualRegisterViewModel.amount.value = amount
    manualRegisterViewModel.calendar.value = calendar

    manualRegisterViewModel.onRegisterButton()

    testSchedulerProvider.triggerActions()

    assertEquals("", manualRegisterViewModel.description.value)
    assertEquals("", manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals("", manualRegisterViewModel.price.value)
    assertEquals("", manualRegisterViewModel.amount.value)
    assertEquals(UiEvent.ShowRegisterSuccess(description), manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)

    manualRegisterViewModel.eventConsumed()

    assertEquals("", manualRegisterViewModel.description.value)
    assertEquals("", manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals("", manualRegisterViewModel.price.value)
    assertEquals("", manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)
  }

  @Test
  fun registerEmptyDescription() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    val calendar = Calendar.getInstance()
    whenever(getCalendarUseCase.getCalendar()).thenReturn(Calendar.getInstance())
    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.just(emptyList()))

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    val description = ""
    val category = "category"
    val place = "place"
    val priceDouble = 1.0
    val price = priceDouble.toString()
    val amountDouble = 1.0
    val amount = amountDouble.toString()
    val expenseDto = expenseMockDto(
      description = description,
      category = category,
      place = place,
      price = priceDouble,
      amount = amountDouble,
      calendar = calendar
    )

    whenever(registerExpenseUseCase.registerExpense(expenseDto)).thenReturn(
      Completable.error(
        RegisterExpenseException(listOf(RegisterExpenseError.EMPTY_DESCRIPTION))
      )
    )

    manualRegisterViewModel.description.value = description
    manualRegisterViewModel.category.value = category
    manualRegisterViewModel.place.value = place
    manualRegisterViewModel.price.value = price
    manualRegisterViewModel.amount.value = amount
    manualRegisterViewModel.calendar.value = calendar

    manualRegisterViewModel.onRegisterButton()

    testSchedulerProvider.triggerActions()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(
      UiState.Error(listOf(UiStateError.ShowEmptyDescriptionError())),
      manualRegisterViewModel.uiState.value
    )

    manualRegisterViewModel.onDescriptionChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)
  }

  @Test
  fun registerCategoryNotExists() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    val calendar = Calendar.getInstance()
    whenever(getCalendarUseCase.getCalendar()).thenReturn(Calendar.getInstance())
    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.just(emptyList()))

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    val description = "description"
    val category = "invalidCategory"
    val place = "place"
    val priceDouble = 1.0
    val price = priceDouble.toString()
    val amountDouble = 1.0
    val amount = amountDouble.toString()
    val expenseDto = expenseMockDto(
      description = description,
      category = category,
      place = place,
      price = priceDouble,
      amount = amountDouble,
      calendar = calendar
    )

    whenever(registerExpenseUseCase.registerExpense(expenseDto)).thenReturn(
      Completable.error(
        RegisterExpenseException(listOf(RegisterExpenseError.CATEGORY_NOT_EXISTS))
      )
    )

    manualRegisterViewModel.description.value = description
    manualRegisterViewModel.category.value = category
    manualRegisterViewModel.place.value = place
    manualRegisterViewModel.price.value = price
    manualRegisterViewModel.amount.value = amount
    manualRegisterViewModel.calendar.value = calendar

    manualRegisterViewModel.onRegisterButton()

    testSchedulerProvider.triggerActions()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(
      UiState.Error(listOf(UiStateError.ShowCategoryNotExistsError())),
      manualRegisterViewModel.uiState.value
    )

    manualRegisterViewModel.onCategoryChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)
  }

  @Test
  fun registerEmptyPlace() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    val calendar = Calendar.getInstance()
    whenever(getCalendarUseCase.getCalendar()).thenReturn(Calendar.getInstance())
    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.just(emptyList()))

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    val description = "description"
    val category = "category"
    val place = ""
    val priceDouble = 1.0
    val price = priceDouble.toString()
    val amountDouble = 1.0
    val amount = amountDouble.toString()
    val expenseDto = expenseMockDto(
      description = description,
      category = category,
      place = place,
      price = priceDouble,
      amount = amountDouble,
      calendar = calendar
    )

    whenever(registerExpenseUseCase.registerExpense(expenseDto)).thenReturn(
      Completable.error(
        RegisterExpenseException(listOf(RegisterExpenseError.EMPTY_PLACE))
      )
    )

    manualRegisterViewModel.description.value = description
    manualRegisterViewModel.category.value = category
    manualRegisterViewModel.place.value = place
    manualRegisterViewModel.price.value = price
    manualRegisterViewModel.amount.value = amount
    manualRegisterViewModel.calendar.value = calendar

    manualRegisterViewModel.onRegisterButton()

    testSchedulerProvider.triggerActions()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(
      UiState.Error(listOf(UiStateError.ShowEmptyPlaceError())),
      manualRegisterViewModel.uiState.value
    )

    manualRegisterViewModel.onPlaceChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)
  }

  @Test
  fun registerInvalidAmount() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    val calendar = Calendar.getInstance()
    whenever(getCalendarUseCase.getCalendar()).thenReturn(Calendar.getInstance())
    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.just(emptyList()))

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    val description = "description"
    val category = "category"
    val place = "place"
    val priceDouble = 1.0
    val price = priceDouble.toString()
    val amountDouble = -0.01
    val amount = amountDouble.toString()
    val expenseDto = expenseMockDto(
      description = description,
      category = category,
      place = place,
      price = priceDouble,
      amount = amountDouble,
      calendar = calendar
    )

    whenever(registerExpenseUseCase.registerExpense(expenseDto)).thenReturn(
      Completable.error(
        RegisterExpenseException(listOf(RegisterExpenseError.INVALID_AMOUNT))
      )
    )

    manualRegisterViewModel.description.value = description
    manualRegisterViewModel.category.value = category
    manualRegisterViewModel.place.value = place
    manualRegisterViewModel.price.value = price
    manualRegisterViewModel.amount.value = amount
    manualRegisterViewModel.calendar.value = calendar

    manualRegisterViewModel.onRegisterButton()

    testSchedulerProvider.triggerActions()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(
      UiState.Error(listOf(UiStateError.ShowInvalidAmountError())),
      manualRegisterViewModel.uiState.value
    )

    manualRegisterViewModel.onAmountChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)
  }

  @Test
  fun registerEmptyPriceAndAmount() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    val calendar = Calendar.getInstance()
    whenever(getCalendarUseCase.getCalendar()).thenReturn(Calendar.getInstance())
    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.just(emptyList()))

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    val description = "description"
    val category = "category"
    val place = "place"
    val priceDouble = 0.0
    val price = ""
    val amountDouble = 0.0
    val amount = ""
    val expenseDto = expenseMockDto(
      description = description,
      category = category,
      place = place,
      price = priceDouble,
      amount = amountDouble,
      calendar = calendar
    )

    whenever(registerExpenseUseCase.registerExpense(expenseDto)).thenReturn(Completable.complete())

    manualRegisterViewModel.description.value = description
    manualRegisterViewModel.category.value = category
    manualRegisterViewModel.place.value = place
    manualRegisterViewModel.price.value = price
    manualRegisterViewModel.amount.value = amount
    manualRegisterViewModel.calendar.value = calendar

    manualRegisterViewModel.onRegisterButton()

    testSchedulerProvider.triggerActions()

    assertEquals("", manualRegisterViewModel.description.value)
    assertEquals("", manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals("", manualRegisterViewModel.price.value)
    assertEquals("", manualRegisterViewModel.amount.value)
    assertEquals(UiEvent.ShowRegisterSuccess(description), manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)
  }

  @Test
  fun onChangeClearError() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    val calendar = Calendar.getInstance()
    whenever(getCalendarUseCase.getCalendar()).thenReturn(Calendar.getInstance())
    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.just(emptyList()))

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    val description = ""
    val category = "category"
    val place = ""
    val priceDouble = 1.0
    val price = priceDouble.toString()
    val amountDouble = -0.01
    val amount = amountDouble.toString()
    val expenseDto = expenseMockDto(
      description = description,
      category = category,
      place = place,
      price = priceDouble,
      amount = amountDouble,
      calendar = calendar
    )

    whenever(registerExpenseUseCase.registerExpense(expenseDto)).thenReturn(
      Completable.error(
        RegisterExpenseException(
          listOf(
            RegisterExpenseError.EMPTY_DESCRIPTION,
            RegisterExpenseError.CATEGORY_NOT_EXISTS,
            RegisterExpenseError.EMPTY_PLACE,
            RegisterExpenseError.INVALID_AMOUNT,
          )
        )
      )
    )

    manualRegisterViewModel.description.value = description
    manualRegisterViewModel.category.value = category
    manualRegisterViewModel.place.value = place
    manualRegisterViewModel.price.value = price
    manualRegisterViewModel.amount.value = amount
    manualRegisterViewModel.calendar.value = calendar

    manualRegisterViewModel.onRegisterButton()

    testSchedulerProvider.triggerActions()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(
      UiState.Error(
        listOf(
          UiStateError.ShowEmptyDescriptionError(),
          UiStateError.ShowCategoryNotExistsError(),
          UiStateError.ShowEmptyPlaceError(),
          UiStateError.ShowInvalidAmountError()
        )
      ), manualRegisterViewModel.uiState.value
    )

    manualRegisterViewModel.onAmountChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(
      UiState.Error(
        listOf(
          UiStateError.ShowEmptyDescriptionError(),
          UiStateError.ShowCategoryNotExistsError(),
          UiStateError.ShowEmptyPlaceError()
        )
      ), manualRegisterViewModel.uiState.value
    )

    manualRegisterViewModel.onRegisterButton()

    testSchedulerProvider.triggerActions()

    manualRegisterViewModel.onDescriptionChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(
      UiState.Error(
        listOf(
          UiStateError.ShowCategoryNotExistsError(),
          UiStateError.ShowEmptyPlaceError(),
          UiStateError.ShowInvalidAmountError()
        )
      ), manualRegisterViewModel.uiState.value
    )

    manualRegisterViewModel.onCategoryChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(
      UiState.Error(
        listOf(
          UiStateError.ShowEmptyPlaceError(),
          UiStateError.ShowInvalidAmountError()
        )
      ), manualRegisterViewModel.uiState.value
    )

    manualRegisterViewModel.onPlaceChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(
      UiState.Error(
        listOf(
          UiStateError.ShowInvalidAmountError()
        )
      ), manualRegisterViewModel.uiState.value
    )

    manualRegisterViewModel.onAmountChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)
  }

  @Test
  fun onChangeWithoutError() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    val calendar = Calendar.getInstance()
    whenever(getCalendarUseCase.getCalendar()).thenReturn(Calendar.getInstance())
    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.just(emptyList()))

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    val description = "description"
    val category = "category"
    val place = "place"
    val priceDouble = 1.0
    val price = priceDouble.toString()
    val amountDouble = 1.0
    val amount = amountDouble.toString()
    val expenseDto = expenseMockDto(
      description = description,
      category = category,
      place = place,
      price = priceDouble,
      amount = amountDouble,
      calendar = calendar
    )

    manualRegisterViewModel.description.value = description
    manualRegisterViewModel.category.value = category
    manualRegisterViewModel.place.value = place
    manualRegisterViewModel.price.value = price
    manualRegisterViewModel.amount.value = amount

    manualRegisterViewModel.onDescriptionChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)

    manualRegisterViewModel.onCategoryChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)

    manualRegisterViewModel.onPlaceChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)

    manualRegisterViewModel.onAmountChange()

    assertEquals(description, manualRegisterViewModel.description.value)
    assertEquals(category, manualRegisterViewModel.category.value)
    assertEquals(place, manualRegisterViewModel.place.value)
    assertEquals(price, manualRegisterViewModel.price.value)
    assertEquals(amount, manualRegisterViewModel.amount.value)
    assertNull(manualRegisterViewModel.uiEvent.value)
    assertEquals(UiState.Ready, manualRegisterViewModel.uiState.value)
  }

  @Test
  fun updateTotal() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.just(emptyList()))

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    val total2 = "2.00 €"
    val total4 = "4.00 €"
    val total6 = "6.00 €"

    whenever(currencyFormatter.format(2.0)).thenReturn(total2)
    whenever(currencyFormatter.format(4.0)).thenReturn(total4)
    whenever(currencyFormatter.format(6.0)).thenReturn(total6)

    manualRegisterViewModel.price.value = "1.0"
    manualRegisterViewModel.amount.value = "2.0"

    manualRegisterViewModel.onPriceChange()
    assertEquals(total2, manualRegisterViewModel.total.value)

    manualRegisterViewModel.price.value = "2.0"
    manualRegisterViewModel.onPriceChange()
    assertEquals(total4, manualRegisterViewModel.total.value)

    manualRegisterViewModel.amount.value = "3.0"
    manualRegisterViewModel.onAmountChange()
    assertEquals(total6, manualRegisterViewModel.total.value)
  }

  @Test
  fun billCardViewModel() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.just(emptyList()))

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    assertEquals(billCardViewModel, manualRegisterViewModel.billCardViewModel)
  }

  @Test
  fun categoriesNames() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    val categoriesDtosList = listOf(
      categoryDtoMock(1), categoryDtoMock(2), categoryDtoMock(3)
    )
    val categoriesNames = categoriesDtosList.map { it.name }
    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(
      Observable.just(
        categoriesDtosList
      )
    )

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    manualRegisterViewModel.categoriesNames.test().assertValue(categoriesNames).assertNoErrors()
  }

  @Test
  fun setNearestPlace() {
    val testSchedulerProvider = TestSchedulerProvider()

    val getCalendarUseCase = mock<GetCalendarUseCase>()
    val observeCategoriesUseCase = mock<ObserveCategoriesUseCase>()
    val registerExpenseUseCase = mock<RegisterExpenseUseCase>()
    val getNearestPlaceUseCase = mock<GetNearestPlaceUseCase>()
    val currencyFormatter = mock<CurrencyFormatter>()
    val billCardViewModel = mock<IBillCardViewModel>()
    val scaffoldViewModelState = mock<ScaffoldViewModelState>()

    whenever(getCalendarUseCase.getCalendar()).thenReturn(Calendar.getInstance())
    whenever(observeCategoriesUseCase.observeCategories()).thenReturn(Observable.empty())

    val manualRegisterViewModel = ManualRegisterViewModel(
      getCalendarUseCase,
      observeCategoriesUseCase,
      registerExpenseUseCase,
      getNearestPlaceUseCase,
      currencyFormatter,
      billCardViewModel,
      scaffoldViewModelState,
      testSchedulerProvider
    )

    val placeDto = placeDtoMock()

    whenever(registerExpenseUseCase.registerExpense(any())).thenReturn(
      Completable.error(
        RegisterExpenseException(
          listOf(
            RegisterExpenseError.EMPTY_DESCRIPTION,
            RegisterExpenseError.EMPTY_PLACE
          )
        )
      )
    )
    whenever(getNearestPlaceUseCase.getNearestPlace()).thenReturn(Maybe.just(placeDto))

    manualRegisterViewModel.onRegisterButton()
    testSchedulerProvider.triggerActions()
    manualRegisterViewModel.onNearestPlaceButton()

    assertEquals("", manualRegisterViewModel.place.value)

    testSchedulerProvider.triggerActions()

    assertEquals(placeDto.name, manualRegisterViewModel.place.value)
    assertEquals(
      UiState.Error(
        listOf(
          UiStateError.ShowEmptyDescriptionError()
        )
      ), manualRegisterViewModel.uiState.value
    )
  }
}