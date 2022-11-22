package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.entity.controller.place.PlaceController
import neuro.expenses.register.entity.controller.place.SavePlace

class AddPlaceProductUseCaseImpl(
  private val getPlaceUseCase: GetPlaceUseCase,
  private val savePlace: SavePlace,
  private val placeController: PlaceController
) : AddPlaceProductUseCase {
  override fun addPlaceProduct(placeId: Long, placeProductDto: PlaceProductDto): Completable {
    return getPlaceUseCase.getPlace(placeId)
      .flatMapSingle { placeDto -> addProduct(placeDto, placeProductDto) }
      .flatMapCompletable { placeDto -> savePlace.savePlace(placeDto.toEntity()) }
  }

  private fun addProduct(placeDto: PlaceDto, placeProductDto: PlaceProductDto): Single<PlaceDto> {
    return placeController.addProduct(placeDto.toEntity(), placeProductDto.toEntity())
      .map { it.toDomain() }
  }
}