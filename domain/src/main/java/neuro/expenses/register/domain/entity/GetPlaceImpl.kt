package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.repository.place.GetPlaceRepository
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.controller.place.GetPlace

class GetPlaceImpl(
  private val getPlaceRepository: GetPlaceRepository
) : GetPlace {
  override fun getPlace(nameLowercase: String): Maybe<Place> {
    return getPlaceRepository.getPlace(nameLowercase).map(PlaceDto::toEntity)
  }
}