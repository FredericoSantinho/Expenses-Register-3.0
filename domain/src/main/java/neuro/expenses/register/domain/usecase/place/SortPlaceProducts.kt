package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceProductDto

interface SortPlaceProducts {
  fun sortPlaceProducts(placeProductsDtos: List<PlaceProductDto>): Single<List<PlaceProductDto>>
}