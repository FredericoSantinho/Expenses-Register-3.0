package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.PricedProductDto
import neuro.expenses.register.domain.entity.Product

class ProductMapperImpl : ProductMapper {
  override fun map(product: Product): PricedProductDto {
    return PricedProductDto(product.description, product.category, product.price, product.iconUrl)
  }

  override fun map(pricedProductDto: PricedProductDto): Product {
    return Product(
      pricedProductDto.description,
      pricedProductDto.category,
      pricedProductDto.price,
      pricedProductDto.iconUrl
    )
  }
}