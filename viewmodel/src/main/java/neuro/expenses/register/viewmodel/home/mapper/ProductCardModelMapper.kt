package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

interface ProductCardModelMapper {
  fun map(placeProductDto: PlaceProductDto, place: String): ProductCardModel
  fun map(productCardModel: ProductCardModel, calendar: Calendar): ExpenseDto
}