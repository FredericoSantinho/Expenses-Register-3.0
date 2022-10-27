package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.entity.Product

interface ProductDtoMapper {
  fun map(product: Product): ProductDto
  fun map(productDto: ProductDto): Product
}