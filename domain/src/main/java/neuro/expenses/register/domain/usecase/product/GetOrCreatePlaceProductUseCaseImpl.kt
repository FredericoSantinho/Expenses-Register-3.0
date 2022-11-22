package neuro.expenses.register.domain.usecase.product

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.entity.controller.product.GetOrCreatePlaceProduct

class GetOrCreatePlaceProductUseCaseImpl(private val getOrCreatePlaceProduct: GetOrCreatePlaceProduct) :
  GetOrCreatePlaceProductUseCase {
  override fun getOrCreatePlaceProduct(placeProductDto: PlaceProductDto): Single<PlaceProductDto> {
    return getOrCreatePlaceProduct.getOrCreatePlaceProduct(
      placeProductDto.productDto.description,
      placeProductDto.category.name,
      placeProductDto.price,
      placeProductDto.productDto.variableAmount,
    ).map { it.toDomain() }
  }
}