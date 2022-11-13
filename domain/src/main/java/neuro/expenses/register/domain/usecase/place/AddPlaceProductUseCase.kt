package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.ProductDto

interface AddPlaceProductUseCase {
  fun addPlaceProduct(place: String, productDto: ProductDto): Completable
}