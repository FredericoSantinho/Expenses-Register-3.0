package neuro.expenses.register.ui.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.ui.home.factory.ProductCardViewModelFactory
import neuro.expenses.register.ui.home.mapper.ProductCardModelMapper
import java.util.*

class ProductsListViewModel(
  private val productCardViewModelFactory: ProductCardViewModelFactory,
  private val productCardModelMapper: ProductCardModelMapper,
  private val calendar: State<Calendar>
) {
  val products = mutableStateOf(emptyList<ProductCardViewModel>())

  fun setProducts(placeDto: PlaceDto) {
    products.value =
      placeDto.products.map { productCardModelMapper.map(it, placeDto.name) }
        .map { productCardViewModelFactory.create(it, calendar) }
  }

  fun clear() {
    products.value.forEach { it.clear() }
  }
}
