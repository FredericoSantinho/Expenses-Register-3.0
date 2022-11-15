package neuro.expenses.register.domain.repository

import neuro.expenses.register.domain.dto.ProductDto

interface SaveProductRepository {
  /**
   * Save a product in database if needed. In case product already exists, no action is taken.
   * @return productId
   */
  fun saveProduct(
    description: String,
    categoryId: Long,
    price: Double,
    defaultAmount: Double
  ): Long

  /**
   * Save a product in database if needed. In case product already exists, no action is taken.
   * @return productId
   */
  fun saveProduct(productDto: ProductDto): Long {
    return saveProduct(
      productDto.description,
      productDto.category.id,
      productDto.price,
      productDto.defaultAmount
    )
  }
}