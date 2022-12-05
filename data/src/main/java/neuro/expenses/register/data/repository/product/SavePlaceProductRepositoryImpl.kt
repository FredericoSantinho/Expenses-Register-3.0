package neuro.expenses.register.data.repository.product

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.data.dao.PlaceProductDao
import neuro.expenses.register.data.mapper.product.toData
import neuro.expenses.register.data.model.placeproduct.PlaceProductCategoryCrossRef
import neuro.expenses.register.data.model.placeproduct.PlaceProductProductCrossRef
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.repository.placeproduct.SavePlaceProductRepository

class SavePlaceProductRepositoryImpl(
  private val placeProductDao: PlaceProductDao
) : SavePlaceProductRepository {
  override fun savePlaceProduct(placeProductDto: PlaceProductDto): Completable {
    return Completable.fromAction {
      placeProductDao.insert(
        placeProductDto.toData(),
        PlaceProductProductCrossRef(placeProductDto.id, placeProductDto.productDto.id),
        PlaceProductCategoryCrossRef(placeProductDto.id, placeProductDto.category.id)
      )
    }
  }
}