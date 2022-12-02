package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.place.SavePlaceRepository

class SavePlaceUseCaseImpl(private val savePlaceRepository: SavePlaceRepository) :
  SavePlaceUseCase {
  override fun savePlace(placeDto: PlaceDto): Completable {
    return savePlaceRepository.savePlace(placeDto)
  }
}