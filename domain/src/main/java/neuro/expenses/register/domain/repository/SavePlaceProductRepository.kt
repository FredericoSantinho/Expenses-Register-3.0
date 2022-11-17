package neuro.expenses.register.domain.repository

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceProductDto

interface SavePlaceProductRepository {

  /**
   * Save a place product in database.
   */
  fun savePlaceProduct(placeProductDto: PlaceProductDto): Completable
}