package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.entity.place.PlaceController

class RemovePlaceProductUseCaseImpl(private val placeController: PlaceController) :
  RemovePlaceProductUseCase {
  override fun removePlaceProduct(placeDto: PlaceDto, placeProductId: Long): Completable {
    return placeController.removePlaceProduct(placeDto.toEntity(), placeProductId).ignoreElement()
  }
}