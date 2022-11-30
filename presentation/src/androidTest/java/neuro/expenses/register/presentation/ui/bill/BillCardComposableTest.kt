package neuro.expenses.register.presentation.ui.bill

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import neuro.expenses.register.presentation.ui.assertContainsColor
import neuro.expenses.register.presentation.ui.assertNotContainsColor
import neuro.expenses.register.presentation.ui.performLongClick
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.bill.BillUiState.UiState
import neuro.expenses.register.viewmodel.bill.IBillCardViewModel
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

internal class BillCardComposableTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun loadingState() {
    val billViewModel = mock<IBillCardViewModel>()

    val iconUrl = mutableStateOf("iconUrl")
    val uiState = mutableStateOf(UiState.Loading)

    whenever(billViewModel.place).doReturn(mutableStateOf(""))
    whenever(billViewModel.time).doReturn(mutableStateOf(""))
    whenever(billViewModel.date).doReturn(mutableStateOf(""))
    whenever(billViewModel.total).doReturn(mutableStateOf(""))
    whenever(billViewModel.iconUrl).doReturn(iconUrl)
    whenever(billViewModel.uiState).doReturn(uiState)

    initComposition(billViewModel)

    val onCard = composeTestRule.onNodeWithTag(BillCardComposableTags.CARD)
    val onPlace = composeTestRule.onNodeWithTag(BillCardComposableTags.PLACE, true)
    val onTime = composeTestRule.onNodeWithTag(BillCardComposableTags.TIME, true)
    val onDate = composeTestRule.onNodeWithTag(BillCardComposableTags.DATE, true)
    val onTotal = composeTestRule.onNodeWithTag(BillCardComposableTags.TOTAL, true)
    val onIcon = composeTestRule.onNodeWithTag(iconUrl.value, true)
    val onCloseBillIcon =
      composeTestRule.onNodeWithTag(BillCardComposableTags.CLOSE_BILL_ICON, true)

    onCard.assertIsDisplayed()
    onPlace.assertIsDisplayed()
    onTime.assertIsDisplayed()
    onDate.assertIsDisplayed()
    onTotal.assertIsDisplayed()
    onIcon.assertIsDisplayed()
    onCloseBillIcon.assertDoesNotExist()

    onIcon.assertHeightIsEqualTo(64.dp)
    onIcon.assertWidthIsEqualTo(64.dp)

    onCard.assertContainsColor(Color.White)
    onPlace.assertNotContainsColor(Color.White)
    onTime.assertNotContainsColor(Color.White)
    onDate.assertNotContainsColor(Color.White)
    onTotal.assertNotContainsColor(Color.White)
    onIcon.assertNotContainsColor(Color.White)
  }

  @Test
  fun billOpenState() {
    onStateReadyTest(UiState.BillOpen)

    val onCloseBillIcon =
      composeTestRule.onNodeWithTag(BillCardComposableTags.CLOSE_BILL_ICON, true)

    onCloseBillIcon.assertIsDisplayed()
  }

  @Test
  fun billClosedState() {
    onStateReadyTest(UiState.BillClosed)

    val onCloseBillIcon =
      composeTestRule.onNodeWithTag(BillCardComposableTags.CLOSE_BILL_ICON, true)

    onCloseBillIcon.assertDoesNotExist()
  }

  private fun onStateReadyTest(_uiState: UiState) {
    val billViewModel = mock<IBillCardViewModel>()

    val place = mutableStateOf("place")
    val time = mutableStateOf("time")
    val date = mutableStateOf("date")
    val total = mutableStateOf("total")
    val iconUrl = mutableStateOf("iconUrl")
    val uiState = mutableStateOf(_uiState)

    whenever(billViewModel.place).doReturn(place)
    whenever(billViewModel.time).doReturn(time)
    whenever(billViewModel.date).doReturn(date)
    whenever(billViewModel.total).doReturn(total)
    whenever(billViewModel.iconUrl).doReturn(iconUrl)
    whenever(billViewModel.uiState).doReturn(uiState)

    initComposition(billViewModel)

    val onCard = composeTestRule.onNodeWithTag(BillCardComposableTags.CARD)
    val onPlace = composeTestRule.onNodeWithTag(BillCardComposableTags.PLACE, true)
    val onTime = composeTestRule.onNodeWithTag(BillCardComposableTags.TIME, true)
    val onDate = composeTestRule.onNodeWithTag(BillCardComposableTags.DATE, true)
    val onTotal = composeTestRule.onNodeWithTag(BillCardComposableTags.TOTAL, true)
    val onIcon = composeTestRule.onNodeWithTag(iconUrl.value, true)

    onCard.assertHasClickAction()

    onCard.assertIsDisplayed()
    onPlace.assertIsDisplayed()
    onTime.assertIsDisplayed()
    onDate.assertIsDisplayed()
    onTotal.assertIsDisplayed()
    onIcon.assertIsDisplayed()

    onPlace.assertTextEquals(place.value)
    onTime.assertTextEquals(time.value)
    onDate.assertTextEquals(date.value)
    onTotal.assertTextEquals(total.value)
    onIcon.assertHeightIsEqualTo(64.dp)
    onIcon.assertWidthIsEqualTo(64.dp)

    onCard.assertContainsColor(Color.White)
    onPlace.assertContainsColor(Color.White)
    onTime.assertContainsColor(Color.White)
    onDate.assertContainsColor(Color.White)
    onTotal.assertContainsColor(Color.White)
    onIcon.assertContainsColor(Color.White)

    verify(billViewModel, times(0)).onCardClick()
    verify(billViewModel, times(0)).onCardLongClick()
    onCard.performClick()
    verify(billViewModel, times(1)).onCardClick()
    verify(billViewModel, times(0)).onCardLongClick()
    onCard.performLongClick()
    verify(billViewModel, times(1)).onCardClick()
    verify(billViewModel, times(1)).onCardLongClick()
  }

  private fun initComposition(billViewModel: IBillCardViewModel) {
    composeTestRule.setContent {
      ExpensesRegisterTheme {
        BillCardComposable(billViewModel)
      }
    }
  }
}