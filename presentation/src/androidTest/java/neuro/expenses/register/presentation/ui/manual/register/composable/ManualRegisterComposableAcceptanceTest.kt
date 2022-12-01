package neuro.expenses.register.presentation.ui.manual.register.composable

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import neuro.expenses.register.presentation.R
import neuro.expenses.register.presentation.di.presentationTestModulesDummyLocation
import neuro.expenses.register.presentation.test.TestSchedulerProvider
import neuro.expenses.register.presentation.ui.bill.BillCardComposableTags
import neuro.expenses.register.presentation.ui.common.composables.datetime.DateTimeComposableTags
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModel
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterViewModel
import neuro.expenses.register.viewmodel.test.PopulatePlacesViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.java.KoinJavaComponent.get
import org.koin.test.KoinTestRule
import java.util.*


internal class ManualRegisterComposableAcceptanceTest {
  @get:Rule
  var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  val composeTestRule = createComposeRule()

  @get:Rule
  val koinTestRule = KoinTestRule.create {
    modules(presentationTestModulesDummyLocation())
  }

  lateinit var context: Context

  lateinit var onRegisterExpenseButton: SemanticsNodeInteraction
  lateinit var onTime: SemanticsNodeInteraction
  lateinit var onDate: SemanticsNodeInteraction
  lateinit var onDescription: SemanticsNodeInteraction
  lateinit var onDescriptionError: SemanticsNodeInteraction
  lateinit var onCategory: SemanticsNodeInteraction
  lateinit var onCategoryError: SemanticsNodeInteraction
  lateinit var onPlace: SemanticsNodeInteraction
  lateinit var onPlaceError: SemanticsNodeInteraction
  lateinit var onPrice: SemanticsNodeInteraction
  lateinit var onPriceError: SemanticsNodeInteraction
  lateinit var onAmount: SemanticsNodeInteraction
  lateinit var onAmountError: SemanticsNodeInteraction
  lateinit var onNearestPlaceButton: SemanticsNodeInteraction

  lateinit var onBillPlace: SemanticsNodeInteraction
  lateinit var onBillTime: SemanticsNodeInteraction
  lateinit var onBillDate: SemanticsNodeInteraction
  lateinit var onBillTotal: SemanticsNodeInteraction
  lateinit var onSnackbar: SemanticsNodeInteraction

  @Before
  fun beforeClass() {
    context = get(Context::class.java)

    onRegisterExpenseButton =
      composeTestRule.onNodeWithTag(ManualRegisterComposableTags.BUTTON_REGISTER_EXPENSE)
    onTime = composeTestRule.onNodeWithTag(DateTimeComposableTags.TIME, true)
    onDate = composeTestRule.onNodeWithTag(DateTimeComposableTags.DATE, true)
    onDescription = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.DESCRIPTION)
    onDescriptionError =
      composeTestRule.onNodeWithTag(ManualRegisterComposableTags.DESCRIPTION_ERROR)
    onCategory = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.CATEGORY)
    onCategoryError = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.CATEGORY_ERROR)
    onPlace = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.PLACE)
    onPlaceError = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.PLACE_ERROR)
    onPrice = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.PRICE)
    onPriceError = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.PRICE_ERROR)
    onAmount = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.AMOUNT)
    onAmountError = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.AMOUNT_ERROR)
    onNearestPlaceButton =
      composeTestRule.onNodeWithTag(ManualRegisterComposableTags.BUTTON_NEAREST_PLACE)

    onBillPlace = composeTestRule.onNodeWithTag(BillCardComposableTags.PLACE, true)
    onBillTime = composeTestRule.onNodeWithTag(BillCardComposableTags.TIME, true)
    onBillDate = composeTestRule.onNodeWithTag(BillCardComposableTags.DATE, true)
    onBillTotal = composeTestRule.onNodeWithTag(BillCardComposableTags.TOTAL, true)
    onSnackbar = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.SNACKBAR, true)
  }

  @Test
  fun fillForm() {
    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)
    manualRegisterViewModel.calendar.value = setupCalendar()

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    val expectedTime = "11h00"
    val expectedDate = "01/01/2022"

    val description = "Tosta Mista Pâo Caseiro"
    val category = "Restau"
    val place = "Bitoque"
    val inputPrice = "3.0"
    val price = "3.0 €"
    val amount = "1.0"

    onDescription.assertTextEquals(context.getString(R.string.description), "")
    onCategory.assertTextEquals(context.getString(R.string.category), "")
    onPlace.assertTextEquals(context.getString(R.string.place), "")
    onPrice.assertTextEquals(context.getString(R.string.price), "")
    onAmount.assertTextEquals(context.getString(R.string.amount), "")
    assertEverythingIsDisplayed()
    assertNoErrorsShown()
    onSnackbar.assertDoesNotExist()

    onTime.assertTextEquals(expectedTime)
    onDate.assertTextEquals(expectedDate)

    onDescription.performTextInput(description)
    onCategory.performTextInput(category)
    onPlace.performTextInput(place)
    onPrice.performTextInput(inputPrice)
    onAmount.performTextInput(amount)

    onTime.assertTextEquals(expectedTime)
    onDate.assertTextEquals(expectedDate)
    onDescription.assertTextEquals(context.getString(R.string.description), description)
    onCategory.assertTextEquals(context.getString(R.string.category), category)
    onPlace.assertTextEquals(context.getString(R.string.place), place)
    onPrice.assertTextEquals(context.getString(R.string.price), price)
    onAmount.assertTextEquals(context.getString(R.string.amount), amount)

    assertEverythingIsDisplayed()
    assertNoErrorsShown()

    onSnackbar.assertDoesNotExist()
  }

  @Test
  fun registerValidExpense() {
    val testSchedulerProvider = get<TestSchedulerProvider>(TestSchedulerProvider::class.java)
    val ioScheduler = testSchedulerProvider.io()
    val uiScheduler = testSchedulerProvider.ui()

    val populatePlacesViewModel = get<PopulatePlacesViewModel>(PopulatePlacesViewModel::class.java)
    val feedLastBillViewModel = get<FeedLastBillViewModel>(FeedLastBillViewModel::class.java)
    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    val description = "Tosta Mista Pâo Caseiro"
    val category = "Restau"
    val place = "Bitoque"
    val price = "3.0"
    val amount = "1.0"
    val iconUrl =
      "https://www.iguaria.com/wp-content/uploads/2016/03/Iguaria_Tosta-de-Bacon-Queijo-Fiambre.jpg"

    val expectedInicialPlace = ""
    val expectedInicialTime = "00h00"
    val expectedInicialDate = "01/01/1970"
    val expectedInicialTotal = "0.00 €"
    val expectedFinalPlace = place
    val expectedFinalTime = "11h00"
    val expectedFinalDate = "01/01/2022"
    val expectedFinalTotal = "3.00 €"

    populatePlacesViewModel.populatePlaces().subscribe()
    feedLastBillViewModel.observe().subscribe()
    manualRegisterViewModel.calendar.value = setupCalendar()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    val onBillIcon = composeTestRule.onNodeWithTag(iconUrl, true)

    onBillPlace.assertTextEquals(expectedInicialPlace)
    onBillTime.assertTextEquals(expectedInicialTime)
    onBillDate.assertTextEquals(expectedInicialDate)
    onBillTotal.assertTextEquals(expectedInicialTotal)

    onDescription.performTextInput(description)
    onCategory.performTextInput(category)
    onPlace.performTextInput(place)
    onPrice.performTextInput(price)
    onAmount.performTextInput(amount)
    onRegisterExpenseButton.performClick()
    onSnackbar.assertDoesNotExist()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    assertEverythingIsDisplayed()
    assertNoErrorsShown()

    onBillPlace.assertTextEquals(expectedFinalPlace)
    onBillTime.assertTextEquals(expectedFinalTime)
    onBillDate.assertTextEquals(expectedFinalDate)
    onBillTotal.assertTextEquals(expectedFinalTotal)
    onBillIcon.assertIsDisplayed()
    onBillIcon.assertHeightIsEqualTo(64.dp)
    onBillIcon.assertWidthIsEqualTo(64.dp)

    onDescription.assertTextEquals(context.getString(R.string.description), "")
    onCategory.assertTextEquals(context.getString(R.string.category), "")
    onPlace.assertTextEquals(context.getString(R.string.place), place)
    onPrice.assertTextEquals(context.getString(R.string.price), "")
    onAmount.assertTextEquals(context.getString(R.string.amount), "")

    // For some strange reason, assertIsDisplayed() fails in spite of the snackbar being visible on the screen.
    // Tested with a static Snackbar always visible on the screen but still no luck.
    onSnackbar.assertExists()
  }

  @Test
  fun registerEmptyDescription() {
    val testSchedulerProvider = get<TestSchedulerProvider>(TestSchedulerProvider::class.java)
    val ioScheduler = testSchedulerProvider.io()
    val uiScheduler = testSchedulerProvider.ui()

    val populatePlacesViewModel = get<PopulatePlacesViewModel>(PopulatePlacesViewModel::class.java)
    val feedLastBillViewModel = get<FeedLastBillViewModel>(FeedLastBillViewModel::class.java)
    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    val description = ""
    val category = "Restau"
    val place = "Bitoque"
    val inputPrice = "3.0"
    val price = "3.0 €"
    val amount = "1.0"

    val expectedInicialPlace = ""
    val expectedInicialTime = "00h00"
    val expectedInicialDate = "01/01/1970"
    val expectedInicialTotal = "0.00 €"
    val expectedFinalPlace = ""
    val expectedFinalTime = "00h00"
    val expectedFinalDate = "01/01/1970"
    val expectedFinalTotal = "0.00 €"

    populatePlacesViewModel.populatePlaces().subscribe()
    feedLastBillViewModel.observe().subscribe()
    manualRegisterViewModel.calendar.value = setupCalendar()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    onSnackbar.assertDoesNotExist()
    onBillPlace.assertTextEquals(expectedInicialPlace)
    onBillTime.assertTextEquals(expectedInicialTime)
    onBillDate.assertTextEquals(expectedInicialDate)
    onBillTotal.assertTextEquals(expectedInicialTotal)

    onDescription.performTextInput(description)
    onCategory.performTextInput(category)
    onPlace.performTextInput(place)
    onPrice.performTextInput(inputPrice)
    onAmount.performTextInput(amount)
    onRegisterExpenseButton.performClick()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    assertEverythingIsDisplayed()
    onDescriptionError.assertIsDisplayed()
    onCategoryError.assertDoesNotExist()
    onPlaceError.assertDoesNotExist()
    onPriceError.assertDoesNotExist()
    onAmountError.assertDoesNotExist()

    onBillPlace.assertTextEquals(expectedFinalPlace)
    onBillTime.assertTextEquals(expectedFinalTime)
    onBillDate.assertTextEquals(expectedFinalDate)
    onBillTotal.assertTextEquals(expectedFinalTotal)

    onDescription.assertTextEquals(context.getString(R.string.description), description)
    onCategory.assertTextEquals(context.getString(R.string.category), category)
    onPlace.assertTextEquals(context.getString(R.string.place), place)
    onPrice.assertTextEquals(context.getString(R.string.price), price)
    onAmount.assertTextEquals(context.getString(R.string.amount), amount)

    onSnackbar.assertDoesNotExist()
  }

  @Test
  fun registerInvalidCategory() {
    val testSchedulerProvider = get<TestSchedulerProvider>(TestSchedulerProvider::class.java)
    val ioScheduler = testSchedulerProvider.io()
    val uiScheduler = testSchedulerProvider.ui()

    val populatePlacesViewModel = get<PopulatePlacesViewModel>(PopulatePlacesViewModel::class.java)
    val feedLastBillViewModel = get<FeedLastBillViewModel>(FeedLastBillViewModel::class.java)
    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    val description = "Tosta Mista Pâo Caseiro"
    val category = "InvalidCategory"
    val place = "Bitoque"
    val inputPrice = "3.0"
    val price = "3.0 €"
    val amount = "1.0"

    val expectedInicialPlace = ""
    val expectedInicialTime = "00h00"
    val expectedInicialDate = "01/01/1970"
    val expectedInicialTotal = "0.00 €"
    val expectedFinalPlace = ""
    val expectedFinalTime = "00h00"
    val expectedFinalDate = "01/01/1970"
    val expectedFinalTotal = "0.00 €"

    populatePlacesViewModel.populatePlaces().subscribe()
    feedLastBillViewModel.observe().subscribe()
    manualRegisterViewModel.calendar.value = setupCalendar()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    onSnackbar.assertDoesNotExist()
    onBillPlace.assertTextEquals(expectedInicialPlace)
    onBillTime.assertTextEquals(expectedInicialTime)
    onBillDate.assertTextEquals(expectedInicialDate)
    onBillTotal.assertTextEquals(expectedInicialTotal)

    onDescription.performTextInput(description)
    onCategory.performTextInput(category)
    onPlace.performTextInput(place)
    onPrice.performTextInput(inputPrice)
    onAmount.performTextInput(amount)
    onRegisterExpenseButton.performClick()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    assertEverythingIsDisplayed()
    onDescriptionError.assertDoesNotExist()
    onCategoryError.assertIsDisplayed()
    onPlaceError.assertDoesNotExist()
    onPriceError.assertDoesNotExist()
    onAmountError.assertDoesNotExist()

    onBillPlace.assertTextEquals(expectedFinalPlace)
    onBillTime.assertTextEquals(expectedFinalTime)
    onBillDate.assertTextEquals(expectedFinalDate)
    onBillTotal.assertTextEquals(expectedFinalTotal)

    onDescription.assertTextEquals(context.getString(R.string.description), description)
    onCategory.assertTextEquals(context.getString(R.string.category), category)
    onPlace.assertTextEquals(context.getString(R.string.place), place)
    onPrice.assertTextEquals(context.getString(R.string.price), price)
    onAmount.assertTextEquals(context.getString(R.string.amount), amount)

    onSnackbar.assertDoesNotExist()
  }

  @Test
  fun registerEmptyPlace() {
    val testSchedulerProvider = get<TestSchedulerProvider>(TestSchedulerProvider::class.java)
    val ioScheduler = testSchedulerProvider.io()
    val uiScheduler = testSchedulerProvider.ui()

    val populatePlacesViewModel = get<PopulatePlacesViewModel>(PopulatePlacesViewModel::class.java)
    val feedLastBillViewModel = get<FeedLastBillViewModel>(FeedLastBillViewModel::class.java)
    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    val description = "Tosta Mista Pâo Caseiro"
    val category = "Restau"
    val place = ""
    val inputPrice = "3.0"
    val price = "3.0 €"
    val amount = "1.0"

    val expectedInicialPlace = ""
    val expectedInicialTime = "00h00"
    val expectedInicialDate = "01/01/1970"
    val expectedInicialTotal = "0.00 €"
    val expectedFinalPlace = ""
    val expectedFinalTime = "00h00"
    val expectedFinalDate = "01/01/1970"
    val expectedFinalTotal = "0.00 €"

    populatePlacesViewModel.populatePlaces().subscribe()
    feedLastBillViewModel.observe().subscribe()
    manualRegisterViewModel.calendar.value = setupCalendar()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    onSnackbar.assertDoesNotExist()
    onBillPlace.assertTextEquals(expectedInicialPlace)
    onBillTime.assertTextEquals(expectedInicialTime)
    onBillDate.assertTextEquals(expectedInicialDate)
    onBillTotal.assertTextEquals(expectedInicialTotal)

    onDescription.performTextInput(description)
    onCategory.performTextInput(category)
    onPlace.performTextInput(place)
    onPrice.performTextInput(inputPrice)
    onAmount.performTextInput(amount)
    onRegisterExpenseButton.performClick()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    assertEverythingIsDisplayed()
    onDescriptionError.assertDoesNotExist()
    onCategoryError.assertDoesNotExist()
    onPlaceError.assertIsDisplayed()
    onPriceError.assertDoesNotExist()
    onAmountError.assertDoesNotExist()

    onBillPlace.assertTextEquals(expectedFinalPlace)
    onBillTime.assertTextEquals(expectedFinalTime)
    onBillDate.assertTextEquals(expectedFinalDate)
    onBillTotal.assertTextEquals(expectedFinalTotal)

    onDescription.assertTextEquals(context.getString(R.string.description), description)
    onCategory.assertTextEquals(context.getString(R.string.category), category)
    onPlace.assertTextEquals(context.getString(R.string.place), place)
    onPrice.assertTextEquals(context.getString(R.string.price), price)
    onAmount.assertTextEquals(context.getString(R.string.amount), amount)

    onSnackbar.assertDoesNotExist()
  }

  @Test
  fun registerInvalidAmount() {
    val testSchedulerProvider = get<TestSchedulerProvider>(TestSchedulerProvider::class.java)
    val ioScheduler = testSchedulerProvider.io()
    val uiScheduler = testSchedulerProvider.ui()

    val populatePlacesViewModel = get<PopulatePlacesViewModel>(PopulatePlacesViewModel::class.java)
    val feedLastBillViewModel = get<FeedLastBillViewModel>(FeedLastBillViewModel::class.java)
    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    val description = "Tosta Mista Pâo Caseiro"
    val category = "Restau"
    val place = "Bitoque"
    val inputPrice = "3.0"
    val price = "3.0 €"
    val amount = ""

    val expectedInicialPlace = ""
    val expectedInicialTime = "00h00"
    val expectedInicialDate = "01/01/1970"
    val expectedInicialTotal = "0.00 €"
    val expectedFinalPlace = ""
    val expectedFinalTime = "00h00"
    val expectedFinalDate = "01/01/1970"
    val expectedFinalTotal = "0.00 €"

    populatePlacesViewModel.populatePlaces().subscribe()
    feedLastBillViewModel.observe().subscribe()
    manualRegisterViewModel.calendar.value = setupCalendar()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    onSnackbar.assertDoesNotExist()
    onBillPlace.assertTextEquals(expectedInicialPlace)
    onBillTime.assertTextEquals(expectedInicialTime)
    onBillDate.assertTextEquals(expectedInicialDate)
    onBillTotal.assertTextEquals(expectedInicialTotal)

    onDescription.performTextInput(description)
    onCategory.performTextInput(category)
    onPlace.performTextInput(place)
    onPrice.performTextInput(inputPrice)
    onAmount.performTextInput(amount)
    onRegisterExpenseButton.performClick()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    assertEverythingIsDisplayed()
    onDescriptionError.assertDoesNotExist()
    onCategoryError.assertDoesNotExist()
    onPlaceError.assertDoesNotExist()
    onPriceError.assertDoesNotExist()
    onAmountError.assertIsDisplayed()

    onBillPlace.assertTextEquals(expectedFinalPlace)
    onBillTime.assertTextEquals(expectedFinalTime)
    onBillDate.assertTextEquals(expectedFinalDate)
    onBillTotal.assertTextEquals(expectedFinalTotal)

    onDescription.assertTextEquals(context.getString(R.string.description), description)
    onCategory.assertTextEquals(context.getString(R.string.category), category)
    onPlace.assertTextEquals(context.getString(R.string.place), place)
    onPrice.assertTextEquals(context.getString(R.string.price), price)
    onAmount.assertTextEquals(context.getString(R.string.amount), amount)

    onSnackbar.assertDoesNotExist()
  }

  @Test
  fun registerMultipleInvalidFields() {
    val testSchedulerProvider = get<TestSchedulerProvider>(TestSchedulerProvider::class.java)
    val ioScheduler = testSchedulerProvider.io()
    val uiScheduler = testSchedulerProvider.ui()

    val populatePlacesViewModel = get<PopulatePlacesViewModel>(PopulatePlacesViewModel::class.java)
    val feedLastBillViewModel = get<FeedLastBillViewModel>(FeedLastBillViewModel::class.java)
    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    val description = ""
    val category = ""
    val place = ""
    val inputPrice = "3.0"
    val price = "3.0 €"
    val amount = ""

    val expectedInicialPlace = ""
    val expectedInicialTime = "00h00"
    val expectedInicialDate = "01/01/1970"
    val expectedInicialTotal = "0.00 €"
    val expectedFinalPlace = ""
    val expectedFinalTime = "00h00"
    val expectedFinalDate = "01/01/1970"
    val expectedFinalTotal = "0.00 €"

    populatePlacesViewModel.populatePlaces().subscribe()
    feedLastBillViewModel.observe().subscribe()
    manualRegisterViewModel.calendar.value = setupCalendar()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    onSnackbar.assertDoesNotExist()
    onBillPlace.assertTextEquals(expectedInicialPlace)
    onBillTime.assertTextEquals(expectedInicialTime)
    onBillDate.assertTextEquals(expectedInicialDate)
    onBillTotal.assertTextEquals(expectedInicialTotal)

    onDescription.performTextInput(description)
    onCategory.performTextInput(category)
    onPlace.performTextInput(place)
    onPrice.performTextInput(inputPrice)
    onAmount.performTextInput(amount)
    onRegisterExpenseButton.performClick()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    assertEverythingIsDisplayed()
    onDescriptionError.assertIsDisplayed()
    onCategoryError.assertIsDisplayed()
    onPlaceError.assertIsDisplayed()
    onPriceError.assertDoesNotExist()
    onAmountError.assertIsDisplayed()

    onBillPlace.assertTextEquals(expectedFinalPlace)
    onBillTime.assertTextEquals(expectedFinalTime)
    onBillDate.assertTextEquals(expectedFinalDate)
    onBillTotal.assertTextEquals(expectedFinalTotal)

    onDescription.assertTextEquals(context.getString(R.string.description), description)
    onCategory.assertTextEquals(context.getString(R.string.category), category)
    onPlace.assertTextEquals(context.getString(R.string.place), place)
    onPrice.assertTextEquals(context.getString(R.string.price), price)
    onAmount.assertTextEquals(context.getString(R.string.amount), amount)

    onSnackbar.assertDoesNotExist()
  }

  @Test
  fun imeAction() {
    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    onSnackbar.assertDoesNotExist()

    manualRegisterViewModel.calendar.value = setupCalendar()

    onDescription.performImeAction()
    onCategory.assertIsFocused()
    onCategory.performImeAction()
    onPlace.assertIsFocused()
    onPlace.performImeAction()
    onPrice.assertIsFocused()
    onPrice.performImeAction()
    onAmount.assertIsFocused()
    onAmount.performImeAction()

    onDescription.assertIsNotFocused()
    onCategory.assertIsNotFocused()
    onPlace.assertIsNotFocused()
    onPrice.assertIsNotFocused()
    onAmount.assertIsFocused()

    assertEverythingIsDisplayed()
    assertNoErrorsShown()

    onDescription.assertTextEquals(context.getString(R.string.description), "")
    onCategory.assertTextEquals(context.getString(R.string.category), "")
    onPlace.assertTextEquals(context.getString(R.string.place), "")
    onPrice.assertTextEquals(context.getString(R.string.price), "")
    onAmount.assertTextEquals(context.getString(R.string.amount), "")

    onSnackbar.assertDoesNotExist()
  }

  @Test
  fun onCategorySelect() {
    val testSchedulerProvider = get<TestSchedulerProvider>(TestSchedulerProvider::class.java)
    val ioScheduler = testSchedulerProvider.io()
    val uiScheduler = testSchedulerProvider.ui()

    val populatePlacesViewModel = get<PopulatePlacesViewModel>(PopulatePlacesViewModel::class.java)
    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    val category = "Restau"
    val categorySubstring = category.substring(0, 4)

    populatePlacesViewModel.populatePlaces().subscribe()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    onSnackbar.assertDoesNotExist()

    onCategory.performTextInput(categorySubstring)
    onCategory.assertTextEquals(context.getString(R.string.category), categorySubstring)
    composeTestRule.onNodeWithTag(ManualRegisterComposableTags.CATEGORY + category).performClick()

    onCategory.assertTextEquals(context.getString(R.string.category), category)

    assertEverythingIsDisplayed()
    assertNoErrorsShown()

    onSnackbar.assertDoesNotExist()
  }

  @Test
  fun onNearestPlaceButton() {
    val testSchedulerProvider = get<TestSchedulerProvider>(TestSchedulerProvider::class.java)
    val ioScheduler = testSchedulerProvider.io()
    val uiScheduler = testSchedulerProvider.ui()

    val populatePlacesViewModel = get<PopulatePlacesViewModel>(PopulatePlacesViewModel::class.java)
    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    onSnackbar.assertDoesNotExist()

    val place = "Bitoque"

    populatePlacesViewModel.populatePlaces().subscribe()

    onPlace.assertTextEquals(context.getString(R.string.place), "")

    onNearestPlaceButton.performClick()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    onPlace.assertTextEquals(context.getString(R.string.place), place)

    assertEverythingIsDisplayed()
    assertNoErrorsShown()

    onSnackbar.assertDoesNotExist()
  }

  @Test
  fun onNearestPlaceButtonEmptyPlaces() {
    val testSchedulerProvider = get<TestSchedulerProvider>(TestSchedulerProvider::class.java)
    val ioScheduler = testSchedulerProvider.io()
    val uiScheduler = testSchedulerProvider.ui()

    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    onSnackbar.assertDoesNotExist()

    onPlace.assertTextEquals(context.getString(R.string.place), "")

    onNearestPlaceButton.performClick()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    onPlace.assertTextEquals(context.getString(R.string.place), "")

    assertEverythingIsDisplayed()
    assertNoErrorsShown()

    onSnackbar.assertDoesNotExist()
  }

  private fun assertNoErrorsShown() {
    onDescriptionError.assertDoesNotExist()
    onCategoryError.assertDoesNotExist()
    onPlaceError.assertDoesNotExist()
    onPriceError.assertDoesNotExist()
    onAmountError.assertDoesNotExist()
  }

  private fun assertEverythingIsDisplayed() {
    onRegisterExpenseButton.assertIsDisplayed()
    onRegisterExpenseButton.assertHasClickAction()
    onTime.assertIsDisplayed()
    onDate.assertIsDisplayed()
    onDescription.assertIsDisplayed()
    onCategory.assertIsDisplayed()
    onPlace.assertIsDisplayed()
    onPrice.assertIsDisplayed()
    onAmount.assertIsDisplayed()

    onBillPlace.assertIsDisplayed()
    onBillTime.assertIsDisplayed()
    onBillDate.assertIsDisplayed()
    onBillTotal.assertIsDisplayed()
  }

  private fun setupCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = Date(0)
    calendar.set(Calendar.HOUR, 11)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    calendar.set(Calendar.MONTH, 0)
    calendar.set(Calendar.YEAR, 2022)
    return calendar
  }
}
