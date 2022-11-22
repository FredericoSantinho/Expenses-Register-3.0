package neuro.expenses.register.domain.repository.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto

interface SavePlaceRepository {
  fun savePlace(placeDto: PlaceDto): Completable
}