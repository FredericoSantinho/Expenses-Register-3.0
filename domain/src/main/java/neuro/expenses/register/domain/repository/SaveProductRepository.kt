package neuro.expenses.register.domain.repository

import neuro.expenses.register.domain.dto.ProductDto

interface SaveProductRepository {

  /**
   * Save a product in database.
   */
  fun saveProduct(productDto: ProductDto)
}