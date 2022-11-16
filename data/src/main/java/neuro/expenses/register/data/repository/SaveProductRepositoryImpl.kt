package neuro.expenses.register.data.repository

import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.data.model.place.PlacePlaceProductCrossRef
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.repository.SaveProductRepository

class SaveProductRepositoryImpl(
  private val productDao: ProductDao,
  private val placeDao: PlaceDao
) : SaveProductRepository {
  override fun saveProduct(placeProductDto: PlaceProductDto) {
    productDao.insert(
      placeProductDto.productDto.description,
      placeProductDto.productDto.iconUrl,
      placeProductDto.id,
      placeProductDto.category.id,
      placeProductDto.price,
      placeProductDto.placeId,
      placeProductDto.productDto.variableAmount
    )
    placeDao.insert(PlacePlaceProductCrossRef(placeProductDto.placeId, placeProductDto.id))
      .blockingGet()
  }
}