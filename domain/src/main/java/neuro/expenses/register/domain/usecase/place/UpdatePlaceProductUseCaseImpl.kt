package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.entity.controller.place.PlaceController

class UpdatePlaceProductUseCaseImpl(private val placeController: PlaceController) :
  UpdatePlaceProductUseCase {
  override fun updatePlaceProduct(
    placeDto: PlaceDto, placeProductDto: PlaceProductDto
  ): Completable {
    return placeController.updatePlaceProduct(placeDto.toEntity(), placeProductDto.toEntity())
      .ignoreElement()
  }
}