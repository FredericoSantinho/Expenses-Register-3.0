package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.mapper.PlaceMapper
import neuro.expenses.register.domain.mapper.ProductMapper
import neuro.expenses.register.entity.controller.PlaceController
import neuro.expenses.register.entity.controller.PlaceControllerImpl

class AddPlaceProductUseCaseImpl(
  private val getPlaceUseCase: GetPlaceUseCase,
  private val savePlaceUseCase: SavePlaceUseCase,
  private val placeMapper: PlaceMapper,
  private val productMapper: ProductMapper,
  private val placeController: PlaceController = PlaceControllerImpl()
) : AddPlaceProductUseCase {
  override fun addPlaceProduct(placeId: Long, productDto: ProductDto): Completable {
    return getPlaceUseCase.getPlace(placeId)
      .map { placeDto -> addProduct(placeDto, productDto) }
      .flatMapCompletable { savePlaceUseCase.savePlace(it) }
  }

  private fun addProduct(placeDto: PlaceDto, productDto: ProductDto): PlaceDto {
    return placeMapper.map(
      placeController.addProduct(
        placeMapper.map(placeDto),
        productMapper.map(productDto)
      )
    )
  }
}