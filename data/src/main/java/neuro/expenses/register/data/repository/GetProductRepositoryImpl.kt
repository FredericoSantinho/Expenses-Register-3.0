package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.data.mapper.product.RoomPlaceProductWithProductAndCategoryMapper
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.repository.GetCategoryRepository
import neuro.expenses.register.domain.repository.GetProductRepository

class GetProductRepositoryImpl(
  private val productDao: ProductDao,
  private val getCategoryRepository: GetCategoryRepository,
  private val roomPlaceProductWithProductAndCategoryMapper: RoomPlaceProductWithProductAndCategoryMapper
) : GetProductRepository {
  override fun getProduct(productId: Long): Maybe<ProductDto> {
    return productDao.getPlaceProduct(productId)
      .map { roomPlaceProductWithProductAndCategoryMapper.map(it) }
  }

  override fun getProduct(description: String, category: String, price: Double): Maybe<ProductDto> {
    return getCategoryRepository.getCategory(category.lowercase()).flatMap { categoryDto ->
      productDao.getProduct(description)
        .flatMap {
          productDao.getPlaceProductWithProductAndCategory(
            it.productId,
            categoryDto.id,
            price
          )
        }
        .map { roomPlaceProductWithProductAndCategoryMapper.map(it) }
    }
  }
}