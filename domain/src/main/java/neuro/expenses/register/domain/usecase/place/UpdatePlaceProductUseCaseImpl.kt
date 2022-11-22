package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.entity.controller.place.PlaceController
import neuro.expenses.register.entity.controller.place.SavePlace

class UpdatePlaceProductUseCaseImpl(
  private val getPlaceUseCase: GetPlaceUseCase,
  private val savePlace: SavePlace,
  private val placeController: PlaceController
) : UpdatePlaceProductUseCase {
  override fun updatePlaceProduct(placeId: Long, placeProductDto: PlaceProductDto): Completable {
    return getPlaceUseCase.getPlace(placeId)
      .flatMapSingle { placeDto -> updateProduct(placeDto, placeProductDto) }
      .flatMapCompletable { placeDto -> savePlace.savePlace(placeDto.toEntity()) }
  }

  private fun updateProduct(
    placeDto: PlaceDto,
    placeProductDto: PlaceProductDto
  ): Single<PlaceDto> {
    return placeController.updateProduct(placeDto.toEntity(), placeProductDto.toEntity())
      .map { it.toDomain() }
  }
}