package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.entity.controller.place.PlaceController

class UpdatePlaceProductUseCaseImpl(
  private val getPlaceUseCase: GetPlaceUseCase, private val placeController: PlaceController
) : UpdatePlaceProductUseCase {
  override fun updatePlaceProduct(placeId: Long, placeProductDto: PlaceProductDto): Completable {
    return getPlaceUseCase.getPlace(placeId)
      .flatMapSingle { placeDto ->
        placeController.updateProduct(placeDto.toEntity(), placeProductDto.toEntity())
      }.ignoreElement()
  }
}