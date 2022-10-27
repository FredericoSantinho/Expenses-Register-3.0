package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.entity.Product

class ProductDtoMapperImpl : ProductDtoMapper {
  override fun map(product: Product): ProductDto {
    return ProductDto(product.description, product.category, product.price, product.iconUrl)
  }

  override fun map(productDto: ProductDto): Product {


    return Product(
      productDto.description,
      productDto.category,
      productDto.price,
      productDto.iconUrl
    )
  }
}