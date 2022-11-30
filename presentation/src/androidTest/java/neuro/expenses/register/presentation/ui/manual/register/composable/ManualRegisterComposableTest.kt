package neuro.expenses.register.presentation.ui.manual.register.composable

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import neuro.expenses.register.presentation.di.presentationTestModules
import neuro.expenses.register.presentation.test.TestSchedulerProvider
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModel
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterViewModel
import neuro.expenses.register.viewmodel.test.PopulatePlacesViewModel
import org.junit.Rule
import org.junit.Test
import org.koin.java.KoinJavaComponent.get
import org.koin.test.KoinTestRule
import java.util.*
import kotlin.test.assertEquals


internal class ManualRegisterComposableTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @get:Rule
  val koinTestRule = KoinTestRule.create {
    modules(presentationTestModules)
  }

  @Test
  fun registerValidExpense() {
    val testSchedulerProvider = get<TestSchedulerProvider>(TestSchedulerProvider::class.java)
    val ioScheduler = testSchedulerProvider.io()
    val uiScheduler = testSchedulerProvider.ui()

    val populatePlacesViewModel = get<PopulatePlacesViewModel>(PopulatePlacesViewModel::class.java)
    val feedLastBillViewModel = get<FeedLastBillViewModel>(FeedLastBillViewModel::class.java)
    val manualRegisterViewModel = get<ManualRegisterViewModel>(ManualRegisterViewModel::class.java)
    val billViewModel = manualRegisterViewModel.billViewModel

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ManualRegisterComposable(manualRegisterViewModel)
      }
    }

    populatePlacesViewModel.populatePlaces().subscribe()
    feedLastBillViewModel.observe().subscribe()
    manualRegisterViewModel.calendar.value = setupCalendar()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    assertEquals(-1, billViewModel.id.value)
    assertEquals("", billViewModel.iconUrl.value)
    assertEquals("", billViewModel.place.value)
    assertEquals("0.00 €", billViewModel.total.value)
    assertEquals("00h00", billViewModel.time.value)
    assertEquals("01/01/1970", billViewModel.date.value)

    val onRegisterExpenseButton =
      composeTestRule.onNodeWithTag(ManualRegisterComposableTags.BUTTON_REGISTER_EXPENSE)
    val onDescription = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.DESCRIPTION)
    val onCategory = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.CATEGORY)
    val onPlace = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.PLACE)
    val onPrice = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.PRICE)
    val onAmount = composeTestRule.onNodeWithTag(ManualRegisterComposableTags.AMOUNT)

    val description = "Tosta Mista Pâo Caseiro"
    val category = "Restau"
    val place = "Bitoque"
    val price = "3.0"
    val amount = "1.0"

    onDescription.performTextInput(description)
    onCategory.performTextInput(category)
    onPlace.performTextInput(place)
    onPrice.performTextInput(price)
    onAmount.performTextInput(amount)
    onRegisterExpenseButton.performClick()

    ioScheduler.triggerActions()
    uiScheduler.triggerActions()

    assertEquals(1L, billViewModel.id.value)
    assertEquals(
      "https://www.iguaria.com/wp-content/uploads/2016/03/Iguaria_Tosta-de-Bacon-Queijo-Fiambre.jpg",
      billViewModel.iconUrl.value
    )
    assertEquals(place, billViewModel.place.value)
    assertEquals("3.00 €", billViewModel.total.value)
    assertEquals("11h00", billViewModel.time.value)
    assertEquals("01/01/2022", billViewModel.date.value)
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
