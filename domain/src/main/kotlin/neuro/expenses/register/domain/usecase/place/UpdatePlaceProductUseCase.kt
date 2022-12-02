package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.dto.PlaceProductDto

interface UpdatePlaceProductUseCase {
  fun updatePlaceProduct(placeDto: PlaceDto, placeProductDto: PlaceProductDto): Completable
}