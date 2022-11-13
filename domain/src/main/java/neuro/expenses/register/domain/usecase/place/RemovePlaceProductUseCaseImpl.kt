package neuro.expenses.register.domain.usecase.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.repository.RemovePlaceProductRepository

class RemovePlaceProductUseCaseImpl(private val removePlaceProductRepository: RemovePlaceProductRepository) :
  RemovePlaceProductUseCase {
  override fun removePlaceProduct(place: String, productId: Long): Completable {
    return removePlaceProductRepository.removePlaceProduct(place, productId)
  }
}