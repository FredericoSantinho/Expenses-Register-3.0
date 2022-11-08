package neuro.expenses.register.ui.home.view.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.ui.home.factory.ProductCardViewModelFactory
import java.util.*

class ProductsListViewModel(
  private val productCardViewModelFactory: ProductCardViewModelFactory,
  private val calendar: State<Calendar>
) {
  val products = mutableStateOf(emptyList<ProductCardViewModel>())

  fun setProducts(placeDto: PlaceDto) {
    products.value =
      placeDto.products.map { productCardViewModelFactory.create(it, placeDto.name, calendar) }
  }

  fun clear() {
    products.value.forEach { it.clear() }
  }
}
