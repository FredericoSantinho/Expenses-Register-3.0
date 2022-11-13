package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.mapper.PlaceMapper
import neuro.expenses.register.domain.mapper.ProductMapper
import neuro.expenses.register.entity.controller.PlaceController
import neuro.expenses.register.entity.controller.PlaceControllerImpl

class UpdatePlaceProductUseCaseImpl(
  private val getPlaceUseCase: GetPlaceUseCase,
  private val savePlaceUseCase: SavePlaceUseCase,
  private val placeMapper: PlaceMapper,
  private val productMapper: ProductMapper,
  private val placeController: PlaceController = PlaceControllerImpl()
) : UpdatePlaceProductUseCase {
  override fun updatePlaceProduct(productDto: ProductDto, place: String): Completable {
    return getPlaceUseCase.getPlace(place).map { placeDto -> updateProduct(placeDto, productDto) }
      .flatMapCompletable { placeDto -> savePlaceUseCase.savePlace(placeDto) }
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