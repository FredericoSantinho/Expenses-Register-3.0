package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.mapper.place.RoomPlaceWithPricedProductsMapper
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.GetPlacesRepository

class GetPlacesRepositoryImpl(
  private val placeDao: PlaceDao,
  private val roomPlaceWithPricedProductsMapper: RoomPlaceWithPricedProductsMapper
) : GetPlacesRepository {
  override fun getPlaces(): Single<List<PlaceDto>> {
    return placeDao.getAll().flattenAsObservable { it }
      .map { roomPlaceWithPricedProductsMapper.map(it) }.toList()
  }
}