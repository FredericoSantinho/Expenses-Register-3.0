package neuro.expenses.register.presentation.ui.manual.register.composable

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import neuro.expenses.register.presentation.R
import neuro.expenses.register.presentation.di.presentationTestModules
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


internal class ManualRegisterComposableTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @get:Rule
  val koinTestRule = KoinTestRule.create {
    modules(presentationTestModules)
  }

  lateinit var context: Context

  lateinit var onRegisterExpenseButton: SemanticsNodeInteraction
  lateinit var onTime: SemanticsNodeInteraction
  lateinit var onDate: SemanticsNodeInteraction
  lateinit var onDescription: SemanticsNodeInteraction
  lateinit var onCategory: SemanticsNodeInteraction
  lateinit var onPlace: SemanticsNodeInteraction
  lateinit var onPrice: SemanticsNodeInteraction
  lateinit var onAmount: SemanticsNodeInteraction

  lateinit var onBillPlace: SemanticsNodeInteraction
  lateinit var onBillTime: SemanticsNodeInteraction
  lateinit var onBillDate: SemanticsNodeInteraction
  lateinit var onBillTotal: SemanticsNodeInteraction

  @Before
  fun beforeClass() {
    context = get(Context::class.java)

    onRegisterExpenseButton =
      composeTestRule.onNodeWithTag(ManualRegisterComposableTags.BUTTON_REGISTER_EXPENSE)
    onTime = composeTestRule.onNodeWithTag(DateTimeComposableTags.TIME, true)
    onDate = composeTestRule.onNodeWithTag(DateTimeComposableTags.DATE, true)
    onDescription = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.DESCRIPTION)
    onCategory = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.CATEGORY)
    onPlace = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.PLACE)
    onPrice = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.PRICE)
    onAmount = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.AMOUNT)

    onBillPlace = composeTestRule.onNodeWithTag(BillCardComposableTags.PLACE, true)
    onBillTime = composeTestRule.onNodeWithTag(BillCardComposableTags.TIME, true)
    onBillDate = composeTestRule.onNodeWithTag(BillCardComposableTags.DATE, true)
    onBillTotal = composeTestRule.onNodeWithTag(BillCardComposableTags.TOTAL, true)
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

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    assertEverythingIsDisplayed()

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
