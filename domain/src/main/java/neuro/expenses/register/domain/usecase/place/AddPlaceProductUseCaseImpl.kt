package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.entity.place.PlaceController

class AddPlaceProductUseCaseImpl(
  private val getPlaceUseCase: GetPlaceUseCase,
  private val placeController: PlaceController
) : AddPlaceProductUseCase {
  override fun addPlaceProduct(placeId: Long, placeProductDto: PlaceProductDto): Completable {
    return getPlaceUseCase.getPlace(placeId)
      .flatMapSingle { placeDto ->
        placeController.addPlaceProduct(placeDto.toEntity(), placeProductDto.toEntity())
      }.ignoreElement()
  }
}