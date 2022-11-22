package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.data.dao.ProductDao
import neuro.expenses.register.data.mapper.product.toData
import neuro.expenses.register.data.model.product.PlaceProductCategoryCrossRef
import neuro.expenses.register.data.model.product.PlaceProductProductCrossRef
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.repository.SavePlaceProductRepository

class SavePlaceProductRepositoryImpl(private val productDao: ProductDao) :
  SavePlaceProductRepository {
  override fun savePlaceProduct(placeProductDto: PlaceProductDto): Completable {
    return Completable.fromAction {
      productDao.insert(
        placeProductDto.toData(),
        PlaceProductProductCrossRef(placeProductDto.id, placeProductDto.productDto.id),
        PlaceProductCategoryCrossRef(placeProductDto.id, placeProductDto.category.id)
      )
    }
  }
}