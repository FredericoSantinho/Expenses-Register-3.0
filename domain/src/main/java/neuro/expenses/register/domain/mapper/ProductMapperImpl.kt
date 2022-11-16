package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.entity.Product

class ProductMapperImpl(private val categoryMapper: CategoryMapper) : ProductMapper {
  override fun map(product: Product): ProductDto {
    return ProductDto(
      product.id,
      product.description,
      categoryMapper.map(product.category),
      product.price,
      product.defaultAmount,
      product.iconUrl,
      product.placeId
    )
  }

  override fun map(productDto: ProductDto): Product {
    return Product(
      productDto.id,
      productDto.description,
      categoryMapper.map(productDto.category),
      productDto.price,
      productDto.defaultAmount,
      productDto.placeId,
      productDto.iconUrl
    )
  }
}