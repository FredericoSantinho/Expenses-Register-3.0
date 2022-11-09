package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.entity.Expense
import neuro.expenses.register.domain.entity.Product

class ProductMapperImpl : ProductMapper {
  override fun map(product: Product): ProductDto {
    return ProductDto(
      product.id,
      product.description,
      product.category,
      product.price,
      product.defaultAmount,
      product.iconUrl
    )
  }

  override fun map(productDto: ProductDto): Product {
    return Product(
      productDto.id,
      productDto.description,
      productDto.category,
      productDto.price,
      productDto.defaultAmount,
      productDto.iconUrl
    )
  }

  override fun map(productId: Long, expense: Expense): Product {
    return Product(productId, expense.description, expense.category, expense.price, expense.amount)
  }
}