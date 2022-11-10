package neuro.expenses.register.ui.home.mapper

import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.ui.home.model.ProductCardModel

interface ProductCardModelMapper {
  fun map(productDto: ProductDto, place: String): ProductCardModel
}