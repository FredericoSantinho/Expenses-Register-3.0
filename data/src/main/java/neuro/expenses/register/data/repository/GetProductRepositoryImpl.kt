package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.data.mapper.product.RoomPlaceProductWithProductAndCategoryMapper
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.repository.GetProductRepository

class GetProductRepositoryImpl(
  private val productDao: ProductDao,
  private val roomPlaceProductWithProductAndCategoryMapper: RoomPlaceProductWithProductAndCategoryMapper
) : GetProductRepository {
  override fun getProduct(productId: Long): Maybe<ProductDto> {
    return productDao.getPlaceProduct(productId)
      .map { roomPlaceProductWithProductAndCategoryMapper.map(it) }
  }
}