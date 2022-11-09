package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.ProductDto
import java.util.*

class PlaceMapperImpl : PlaceMapper {
  private val ZERO = LatLngDto(0.0, 0.0)

  override fun map(expenseDto: ExpenseDto): PlaceDto {
    val products = Collections.singletonList(
      ProductDto(
        0,
        expenseDto.description,
        expenseDto.category,
        expenseDto.price,
        expenseDto.amount
      )
    )
    return PlaceDto(expenseDto.place, products, ZERO)
  }
}