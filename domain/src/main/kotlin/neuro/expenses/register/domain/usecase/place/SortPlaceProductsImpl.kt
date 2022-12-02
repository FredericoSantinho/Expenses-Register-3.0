package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceProductDto

class SortPlaceProductsImpl : SortPlaceProducts {
  override fun sortPlaceProducts(placeProductsDtos: List<PlaceProductDto>): Single<List<PlaceProductDto>> {
    return Single.fromCallable { placeProductsDtos.sortedBy { it.productDto.description } }
  }
}