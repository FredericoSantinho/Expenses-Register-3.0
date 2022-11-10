package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.viewmodel.home.model.ProductCardModel

interface ProductCardModelMapper {
  fun map(productDto: ProductDto, place: String): ProductCardModel
}