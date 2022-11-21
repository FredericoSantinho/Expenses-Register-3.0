package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.viewmodel.model.search.PlaceSearchSuggestionModel
import neuro.expenses.register.viewmodel.model.search.ProductSearchSuggestionModel

interface SearchSuggestionModelMapper {
  fun map(placeProductDto: PlaceProductDto): ProductSearchSuggestionModel
  fun map(placeDto: PlaceDto, onPlaceSearchSuggestion: (Long) -> (Unit)): PlaceSearchSuggestionModel
}