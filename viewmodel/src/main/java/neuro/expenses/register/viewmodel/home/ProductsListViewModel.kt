package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.viewmodel.common.filter.DefaultStringFilter
import neuro.expenses.register.viewmodel.common.filter.StringFilter
import neuro.expenses.register.viewmodel.home.factory.PlaceProductCardViewModelFactory
import neuro.expenses.register.viewmodel.home.mapper.PlaceProductCardModelMapper

class ProductsListViewModel(
  private val placeProductCardViewModelFactory: PlaceProductCardViewModelFactory,
  private val placeProductCardModelMapper: PlaceProductCardModelMapper,
  private val stringFilter: StringFilter = DefaultStringFilter()
) {
  val products = mutableStateOf(emptyList<PlaceProductCardViewModel>())

  fun setProducts(placeDto: PlaceDto, query: String = "") {
    products.value =
      placeDto.products.filter { stringFilter.filter(query, it.productDto.description) }
        .map { placeProductCardModelMapper.map(it, placeDto.name) }
        .map { placeProductCardViewModelFactory.create(it) }
  }
}
