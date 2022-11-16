package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.mapper.PlaceMapper
import neuro.expenses.register.domain.mapper.ProductMapper
import neuro.expenses.register.entity.controller.place.PlaceController
import neuro.expenses.register.entity.controller.place.PlaceControllerImpl
import neuro.expenses.register.entity.controller.place.SavePlace

class UpdatePlaceProductUseCaseImpl(
  private val getPlaceUseCase: GetPlaceUseCase,
  private val savePlace: SavePlace,
  private val placeMapper: PlaceMapper,
  private val productMapper: ProductMapper,
  private val placeController: PlaceController = PlaceControllerImpl()
) : UpdatePlaceProductUseCase {
  override fun updatePlaceProduct(productDto: ProductDto, placeId: Long): Completable {
    return getPlaceUseCase.getPlace(placeId).map { placeDto -> updateProduct(placeDto, productDto) }
      .flatMapCompletable { placeDto -> savePlace.savePlace(placeMapper.map(placeDto)) }
  }

  private fun updateProduct(placeDto: PlaceDto, productDto: ProductDto): PlaceDto {
    return placeMapper.map(
      placeController.updateProduct(
        placeMapper.map(placeDto),
        productMapper.map(productDto)
      )
    )
  }
}