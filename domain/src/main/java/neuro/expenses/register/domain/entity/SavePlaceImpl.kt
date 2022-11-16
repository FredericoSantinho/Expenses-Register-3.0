package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.mapper.PlaceMapper
import neuro.expenses.register.domain.repository.SavePlaceRepository
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.controller.place.SavePlace

class SavePlaceImpl(
  private val savePlaceRepository: SavePlaceRepository,
  private val placeMapper: PlaceMapper
) : SavePlace {
  override fun savePlace(place: Place): Completable {
    return savePlaceRepository.savePlace(placeMapper.map(place))
  }
}