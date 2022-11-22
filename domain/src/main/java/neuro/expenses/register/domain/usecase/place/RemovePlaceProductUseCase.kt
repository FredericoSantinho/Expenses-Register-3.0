package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto

interface RemovePlaceProductUseCase {
  fun removePlaceProduct(placeDto: PlaceDto, placeProductId: Long): Completable
}