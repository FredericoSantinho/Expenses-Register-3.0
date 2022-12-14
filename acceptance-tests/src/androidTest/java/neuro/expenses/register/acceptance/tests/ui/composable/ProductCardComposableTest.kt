package neuro.expenses.register.acceptance.tests.ui.composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import neuro.expenses.register.acceptance.tests.ui.performLongClick
import neuro.expenses.register.presentation.ui.home.composable.PlaceProductCardComposable
import neuro.expenses.register.presentation.ui.home.composable.ProductCardComposableTags
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.home.IProductCardViewModel
import neuro.expenses.register.viewmodel.model.CategoryModel
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class ProductCardComposableTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun productCardComposable() {
    val description = "Tosta Mista Pâo Caseiro"
    val category = "Restau"
    val price = "4.20 €"
    val iconUrl =
      "https://www.iguaria.com/wp-content/uploads/2016/03/Iguaria_Tosta-de-Bacon-Queijo-Fiambre.jpg"

    val productCardViewModel = mock<IProductCardViewModel>()

    whenever(productCardViewModel.description).thenReturn(mutableStateOf(description))
    whenever(productCardViewModel.categoryModel).thenReturn(
      mutableStateOf(
        CategoryModel(
          1, category, "iconUrl"
        )
      )
    )
    whenever(productCardViewModel.price).thenReturn(mutableStateOf(price))
    whenever(productCardViewModel.iconUrl).thenReturn(mutableStateOf(iconUrl))

    composeTestRule.setContent {
      ExpensesRegisterTheme {
        PlaceProductCardComposable(productCardViewModel)
      }
    }

    val onCard = composeTestRule.onNodeWithTag(ProductCardComposableTags.CARD)
    val onDescription =
      composeTestRule.onNodeWithTag(ProductCardComposableTags.PRODUCT_DESCRIPTION, true)
    val onCategory = composeTestRule.onNodeWithTag(ProductCardComposableTags.CATEGORY, true)
    val onPrice = composeTestRule.onNodeWithTag(ProductCardComposableTags.PRICE, true)
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