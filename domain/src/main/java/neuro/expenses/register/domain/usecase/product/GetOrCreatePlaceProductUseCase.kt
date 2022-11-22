package neuro.expenses.register.domain.usecase.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceProductDto

interface GetOrCreatePlaceProductUseCase {
  fun getOrCreatePlaceProduct(placeProductDto: PlaceProductDto): Single<PlaceProductDto>
}