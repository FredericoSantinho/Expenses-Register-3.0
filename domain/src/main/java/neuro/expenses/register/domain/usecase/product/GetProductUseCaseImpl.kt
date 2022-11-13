package neuro.expenses.register.domain.usecase.product

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.repository.GetProductRepository

class GetProductUseCaseImpl(private val getProductRepository: GetProductRepository) : GetProductUseCase {
  override fun getProduct(productId: Long): Maybe<ProductDto> {
    return getProductRepository.getProduct(productId)
  }
}