package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

interface ProductCardModelMapper {
  fun map(productDto: ProductDto, place: String): ProductCardModel
  fun map(productCardModel: ProductCardModel, calendar: Calendar): ExpenseDto
}