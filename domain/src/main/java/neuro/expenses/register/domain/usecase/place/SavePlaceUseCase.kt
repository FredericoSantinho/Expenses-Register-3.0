package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto

interface SavePlaceUseCase {
  fun savePlace(placeDto: PlaceDto): Completable
}