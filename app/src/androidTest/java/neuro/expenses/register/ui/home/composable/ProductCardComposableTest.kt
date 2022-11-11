package neuro.expenses.register.ui.home.composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import neuro.expenses.register.ui.performLongClick
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.home.IProductCardViewModel
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

internal class ProductCardComposableTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun test() {
    val description = "Tosta Mista Pâo Caseiro"
    val category = "Restau"
    val price = "4.20 €"
    val iconUrl =
      "https://www.iguaria.com/wp-content/uploads/2016/03/Iguaria_Tosta-de-Bacon-Queijo-Fiambre.jpg"

    val productCardViewModel = mock<IProductCardViewModel>()

    whenever(productCardViewModel.description).doReturn(mutableStateOf(description))
    whenever(productCardViewModel.category).doReturn(mutableStateOf(category))
    whenever(productCardViewModel.price).doReturn(mutableStateOf(price))
    whenever(productCardViewModel.iconUrl).doReturn(mutableStateOf(iconUrl))

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        ProductCardComposable(productCardViewModel)
      }
    }

    val onCard = composeTestRule.onNodeWithTag(ProductCardTags.CARD)
    val onDescription = composeTestRule.onNodeWithTag(ProductCardTags.PRODUCT_DESCRIPTION, true)
    val onCategory = composeTestRule.onNodeWithTag(ProductCardTags.CATEGORY, true)
    val onPrice = composeTestRule.onNodeWithTag(ProductCardTags.PRICE, true)
    val onIcon = composeTestRule.onNodeWithTag(productCardViewModel.iconUrl.value, true)

    onCard.assertHasClickAction()

    onCard.assertIsDisplayed()
    onDescription.assertIsDisplayed()
    onCategory.assertIsDisplayed()
    onPrice.assertIsDisplayed()
    onIcon.assertIsDisplayed()

    onDescription.assertTextEquals(description)
    onCategory.assertTextEquals(category)
    onPrice.assertTextEquals(price)
    onIcon.assertHeightIsEqualTo(48.dp)
    onIcon.assertWidthIsEqualTo(48.dp)

    verify(productCardViewModel, times(0)).onCardClick()
    verify(productCardViewModel, times(0)).onCardLongClick()
    onCard.performClick()
    verify(productCardViewModel, times(1)).onCardClick()
    verify(productCardViewModel, times(0)).onCardLongClick()
    onCard.performLongClick()
    verify(productCardViewModel, times(1)).onCardClick()
    verify(productCardViewModel, times(1)).onCardLongClick()
  }
}