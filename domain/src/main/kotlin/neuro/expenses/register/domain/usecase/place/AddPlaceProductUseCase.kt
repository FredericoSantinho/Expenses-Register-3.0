package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceProductDto

interface AddPlaceProductUseCase {
  /**
   * Add a Place Product to a Place.
   *
   * @param placeId Place id.
   * @param placeProductDto PlaceProduct to add.
   * @return Completable that completes if operation succeeds.
   */
  fun addPlaceProduct(placeId: Long, placeProductDto: PlaceProductDto): Completable
}