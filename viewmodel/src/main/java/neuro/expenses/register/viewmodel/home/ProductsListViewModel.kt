package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.viewmodel.common.filter.DefaultStringFilter
import neuro.expenses.register.viewmodel.common.filter.StringFilter
import neuro.expenses.register.viewmodel.home.factory.ProductCardViewModelFactory
import neuro.expenses.register.viewmodel.home.mapper.ProductCardModelMapper

class ProductsListViewModel(
  private val productCardViewModelFactory: ProductCardViewModelFactory,
  private val productCardModelMapper: ProductCardModelMapper,
  private val stringFilter: StringFilter = DefaultStringFilter()
) {
  val products = mutableStateOf(emptyList<ProductCardViewModel>())

  fun setProducts(placeDto: PlaceDto, query: String = "") {
    products.value =
      placeDto.products.filter { stringFilter.filter(query, it.productDto.description) }
        .map { productCardModelMapper.map(it, placeDto.name) }
        .map { productCardViewModelFactory.create(it) }
  }
}
