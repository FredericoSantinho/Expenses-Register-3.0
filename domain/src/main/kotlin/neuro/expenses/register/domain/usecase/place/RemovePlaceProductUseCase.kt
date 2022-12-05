package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto

interface RemovePlaceProductUseCase {
  /**
   * Remove a Place Product from a Place.
   *
   * @param placeDto Place.
   * @param placeProductId PlaceProduct id.
   * @return Completable that completes if operation succeeds.
   */
  fun removePlaceProduct(placeDto: PlaceDto, placeProductId: Long): Completable
}