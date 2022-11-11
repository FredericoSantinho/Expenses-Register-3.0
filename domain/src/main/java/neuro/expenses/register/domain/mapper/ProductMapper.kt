package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.entity.Product

interface ProductMapper {
  fun map(product: Product): ProductDto
  fun map(productDto: ProductDto): Product
}