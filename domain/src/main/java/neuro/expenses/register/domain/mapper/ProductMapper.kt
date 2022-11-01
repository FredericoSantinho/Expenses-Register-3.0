package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.PricedProductDto
import neuro.expenses.register.domain.entity.Product

interface ProductMapper {
  fun map(product: Product): PricedProductDto
  fun map(pricedProductDto: PricedProductDto): Product
}