package neuro.expenses.register.data.repository

import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.data.model.place.PlacePlaceProductCrossRef
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.repository.SaveProductRepository

class SaveProductRepositoryImpl(
  private val productDao: ProductDao,
  private val placeDao: PlaceDao
) : SaveProductRepository {
  override fun saveProduct(productDto: ProductDto) {
    productDao.insert(
      productDto.description,
      productDto.iconUrl,
      productDto.id,
      productDto.category.id,
      productDto.price,
      productDto.placeId,
      productDto.variableAmount
    )
    placeDao.insert(PlacePlaceProductCrossRef(productDto.placeId, productDto.id)).blockingGet()
  }
}