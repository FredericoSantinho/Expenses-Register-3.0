package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.viewmodel.home.factory.ProductCardViewModelFactory
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapper

class ProductsListViewModel(
  private val productCardViewModelFactory: ProductCardViewModelFactory,
  private val productCardModelMapper: ProductCardModelMapper,
) {
  val products = mutableStateOf(emptyList<ProductCardViewModel>())

  fun setProducts(placeDto: PlaceDto) {
    products.value =
      placeDto.products.map { productCardModelMapper.map(it, placeDto.name) }
        .map { productCardViewModelFactory.create(it) }
  }
}
