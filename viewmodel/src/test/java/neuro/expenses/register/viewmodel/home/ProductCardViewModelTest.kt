package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.common.model.CategoryModel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.*
import java.util.*

internal class ProductCardViewModelTest {

  @Test
  fun test() {
    val onProductCardClick = mock<OnProductCardClick>()
    val id = 1L
    val description = "desc"
    val categoryModel = CategoryModel(1L, "cat")
    val place = "place"
    val price = "1.00 €"
    val amount = 1.0
    val iconUrl = "url"
    val productCardModel =
      ProductCardModel(id, description, categoryModel, place, price, iconUrl)
    val calendar = mutableStateOf(Calendar.getInstance())

    val productCardViewModel = ProductCardViewModel(onProductCardClick, productCardModel, calendar)

    assertEquals(calendar, productCardViewModel.calendar)
    assertEquals(description, productCardViewModel.description.value)
    assertEquals(categoryModel, productCardViewModel.categoryModel.value)
    assertEquals(place, productCardViewModel.place.value)
    assertEquals(price, productCardViewModel.price.value)
    assertEquals(amount, productCardViewModel.amount.value, 0.0)
    assertEquals(iconUrl, productCardViewModel.iconUrl.value)

    verifyNoInteractions(onProductCardClick)
    productCardViewModel.onCardClick()
    verify(onProductCardClick, times(1)).onProductCardClick(
      eq(productCardModel),
      same(calendar.value)
    )
    verify(onProductCardClick, times(0)).onProductCardLongClick(any(), any())

    productCardViewModel.onCardLongClick()
    verify(onProductCardClick, times(1)).onProductCardClick(
      eq(productCardModel),
      same(calendar.value)
    )
    verify(onProductCardClick, times(1)).onProductCardLongClick(
      eq(productCardModel),
      same(calendar.value)
    )
  }
}