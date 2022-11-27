package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.viewmodel.home.model.PlaceProductCardModel
import java.util.*

interface PlaceProductCardModelMapper {
  fun map(placeProductDto: PlaceProductDto, place: String): PlaceProductCardModel
  fun map(placeProductCardModel: PlaceProductCardModel, calendar: Calendar): ExpenseDto
}