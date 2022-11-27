package neuro.expenses.register.viewmodel.home

import neuro.expenses.register.viewmodel.home.model.PlaceProductCardModel
import neuro.expenses.register.viewmodel.model.CategoryModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions

internal class PlaceProductCardViewModelTest {

  @Test
  fun test() {
    val onProductCardClick = mock<OnProductCardClick>()
    val id = 1L
    val description = "desc"
    val categoryModel = CategoryModel(1L, "cat", "")
    val place = "place"
    val price = "1.00 €"
    val amount = 1.0
    val iconUrl = "url"
    val placeProductCardModel =
      PlaceProductCardModel(id, description, categoryModel, place, price, iconUrl)

    val placeProductCardViewModel =
      PlaceProductCardViewModel(onProductCardClick, placeProductCardModel)

    assertEquals(description, placeProductCardViewModel.description.value)
    assertEquals(categoryModel, placeProductCardViewModel.categoryModel.value)
    assertEquals(place, placeProductCardViewModel.place.value)
    assertEquals(price, placeProductCardViewModel.price.value)
    assertEquals(amount, placeProductCardViewModel.amount.value, 0.0)
    assertEquals(iconUrl, placeProductCardViewModel.iconUrl.value)

    verifyNoInteractions(onProductCardClick)
    placeProductCardViewModel.onCardClick()
    verify(onProductCardClick, times(1)).onProductCardClick(
      eq(placeProductCardModel)
    )
    verify(onProductCardClick, times(0)).onProductCardLongClick(any())

    placeProductCardViewModel.onCardLongClick()
    verify(onProductCardClick, times(1)).onProductCardClick(
      eq(placeProductCardModel)
    )
    verify(onProductCardClick, times(1)).onProductCardLongClick(
      eq(placeProductCardModel)
    )
  }
}