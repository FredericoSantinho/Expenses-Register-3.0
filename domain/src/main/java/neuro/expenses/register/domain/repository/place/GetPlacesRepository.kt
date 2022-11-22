package neuro.expenses.register.domain.repository.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceDto

interface GetPlacesRepository {
  fun getPlaces(): Single<List<PlaceDto>>
}