package neuro.expenses.register.viewmodel.home

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.viewmodel.common.filter.DefaultStringFilter
import neuro.expenses.register.viewmodel.common.filter.StringFilter
import neuro.expenses.register.viewmodel.home.mapper.PlaceProductCardModelMapper
import neuro.expenses.register.viewmodel.home.model.PlaceProductCardModel

class ProductsListViewModel(
  private val placeProductCardModelMapper: PlaceProductCardModelMapper,
  private val onProductCardClick: (PlaceProductCardModel) -> Unit,
  private val onProductCardLongClick: (PlaceProductCardModel) -> Unit,
  private val stringFilter: StringFilter = DefaultStringFilter()
) {
  val products = mutableStateOf(emptyList<PlaceProductCardViewModel>())

  fun setProducts(placeDto: PlaceDto, query: String = "") {
    products.value =
      placeDto.products.filter { stringFilter.filter(query, it.productDto.description) }
        .map { placeProductCardModelMapper.map(it, placeDto.name) }
        .map { PlaceProductCardViewModel(it, onProductCardClick, onProductCardLongClick) }
  }
}
