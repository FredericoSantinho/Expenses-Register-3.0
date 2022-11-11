package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.viewmodel.home.factory.ProductCardViewModelFactory
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapper
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
}
