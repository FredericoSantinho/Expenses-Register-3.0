package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.PlaceProductDto

interface AddPlaceProductUseCase {
  fun addPlaceProduct(placeProductDto: PlaceProductDto): Completable
}