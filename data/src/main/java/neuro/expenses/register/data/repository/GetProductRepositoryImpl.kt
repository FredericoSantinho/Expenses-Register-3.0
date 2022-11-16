package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.data.mapper.product.toDomain
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.repository.GetCategoryRepository
import neuro.expenses.register.domain.repository.GetProductRepository

class GetProductRepositoryImpl(
  private val productDao: ProductDao,
  private val getCategoryRepository: GetCategoryRepository
) : GetProductRepository {
  override fun getProduct(productId: Long): Maybe<PlaceProductDto> {
    return productDao.getPlaceProduct(productId)
      .map { roomPlaceProductWithProductAndCategory -> roomPlaceProductWithProductAndCategory.toDomain() }
  }

  override fun getProduct(
    description: String,
    category: String,
    price: Double,
    placeId: Long
  ): Maybe<PlaceProductDto> {
    return getCategoryRepository.getCategory(category.lowercase()).flatMap { categoryDto ->
      productDao.getProduct(description)
        .flatMap { roomProduct ->
          productDao.getPlaceProductWithProductAndCategory(
            roomProduct.productId,
            categoryDto.id,
            price,
            placeId
          )
        }
        .map { roomPlaceProductWithProductAndCategory -> roomPlaceProductWithProductAndCategory.toDomain() }
    }
  }
}