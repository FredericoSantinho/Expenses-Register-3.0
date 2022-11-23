package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatter
import neuro.expenses.register.viewmodel.model.search.PlaceSearchSuggestionModel
import neuro.expenses.register.viewmodel.model.search.ProductSearchSuggestionModel

class SearchSuggestionModelMapperImpl(private val currencyFormatter: CurrencyFormatter) :
  SearchSuggestionModelMapper {
  override fun map(placeProductDto: PlaceProductDto): ProductSearchSuggestionModel {
    val price = currencyFormatter.format(placeProductDto.price)

    return ProductSearchSuggestionModel(
      placeProductDto.productDto.description,
      price,
      placeProductDto.productDto.iconUrl
    )
  }

  override fun map(
    placeDto: PlaceDto,
    onPlaceSearchSuggestion: (Long) -> (Unit)
  ): PlaceSearchSuggestionModel {
    return PlaceSearchSuggestionModel(placeDto.id, placeDto.name, onPlaceSearchSuggestion)
  }
}