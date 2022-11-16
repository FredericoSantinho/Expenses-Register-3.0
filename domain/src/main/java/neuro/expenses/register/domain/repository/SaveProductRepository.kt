package neuro.expenses.register.domain.repository

import neuro.expenses.register.domain.dto.PlaceProductDto

interface SaveProductRepository {

  /**
   * Save a product in database.
   */
  fun saveProduct(placeProductDto: PlaceProductDto)
}