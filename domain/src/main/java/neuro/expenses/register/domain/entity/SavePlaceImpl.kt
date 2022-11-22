package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.usecase.place.SavePlaceUseCase
import neuro.expenses.register.entity.Place
import neuro.expenses.register.entity.controller.place.SavePlace

class SavePlaceImpl(
  private val savePlaceUseCase: SavePlaceUseCase
) : SavePlace {
  override fun savePlace(place: Place): Completable {
    return savePlaceUseCase.savePlace(place.toDomain())
  }
}