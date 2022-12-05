package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto

interface UpdatePlaceProductUseCase {
  /**
   * Update a Place Product in a Place.
   *
   * @param placeDto Place.
   * @param placeProductDto updated PlaceProduct.
   * @return Completable that completes if operation succeeds.
   */
  fun updatePlaceProduct(placeDto: PlaceDto, placeProductDto: PlaceProductDto): Completable
}