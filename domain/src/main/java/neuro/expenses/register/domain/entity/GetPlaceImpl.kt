package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.mapper.PlaceMapper
import neuro.expenses.register.domain.repository.GetPlaceRepository
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.controller.place.GetPlace

class GetPlaceImpl(
  private val getPlaceRepository: GetPlaceRepository,
  private val placeMapper: PlaceMapper
) : GetPlace {
  override fun getPlace(nameLowercase: String): Maybe<Place> {
    return getPlaceRepository.getPlace(nameLowercase).map { placeMapper.map(it) }
  }
}