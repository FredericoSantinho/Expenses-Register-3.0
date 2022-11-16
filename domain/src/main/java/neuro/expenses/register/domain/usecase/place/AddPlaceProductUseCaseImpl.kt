package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.entity.controller.place.PlaceController
import neuro.expenses.register.entity.controller.place.PlaceControllerImpl
import neuro.expenses.register.entity.controller.place.SavePlace

class AddPlaceProductUseCaseImpl(
  private val getPlaceUseCase: GetPlaceUseCase,
  private val savePlace: SavePlace,
  private val placeController: PlaceController = PlaceControllerImpl()
) : AddPlaceProductUseCase {
  override fun addPlaceProduct(placeId: Long, productDto: ProductDto): Completable {
    return getPlaceUseCase.getPlace(placeId).map { placeDto -> addProduct(placeDto, productDto) }
      .flatMapCompletable { placeDto -> savePlace.savePlace(placeDto.toEntity()) }
  }

  private fun addProduct(placeDto: PlaceDto, productDto: ProductDto): PlaceDto {
    return placeController.addProduct(placeDto.toEntity(), productDto.toEntity()).toDomain()
  }
}