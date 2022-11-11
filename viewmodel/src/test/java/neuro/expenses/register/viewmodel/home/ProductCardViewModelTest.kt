package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.same
import org.mockito.kotlin.verifyNoInteractions
import java.util.*

class ProductCardViewModelTest {

  @Test
  fun test() {
    val homeViewModel = mock<IHomeViewModel>()
    val description = "desc"
    val category = "cat"
    val place = "place"
    val price = "1.00 €"
    val amount = 1.0
    val iconUrl = "url"

    val productCardModel = ProductCardModel(description, category, place, price, amount, iconUrl)
    val calendar = mutableStateOf(Calendar.getInstance())

    val productCardViewModel = ProductCardViewModel(homeViewModel, productCardModel, calendar)

    Assert.assertEquals(description, productCardViewModel.description.value)
    Assert.assertEquals(category, productCardViewModel.category.value)
    Assert.assertEquals(place, productCardViewModel.place.value)
    Assert.assertEquals(price, productCardViewModel.price.value)
    Assert.assertEquals(amount, productCardViewModel.amount.value, 0.0)
    Assert.assertEquals(iconUrl, productCardViewModel.iconUrl.value)

    verifyNoInteractions(homeViewModel)
    productCardViewModel.onCardClick()
    println(calendar.value)

    verify(homeViewModel, times(1)).onProductCardClick(any(), same(calendar.value))
  }
}