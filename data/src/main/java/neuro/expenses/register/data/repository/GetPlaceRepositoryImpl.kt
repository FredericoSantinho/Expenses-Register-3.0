package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.mapper.place.RoomPlaceWithPlaceProductsMapper
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.GetPlaceRepository

class GetPlaceRepositoryImpl(
  private val placeDao: PlaceDao,
  private val roomPlaceWithPlaceProductsMapper: RoomPlaceWithPlaceProductsMapper
) : GetPlaceRepository {
  override fun getPlace(placeId: Long): Maybe<PlaceDto> {
    return placeDao.getPlace(placeId).map { roomPlaceWithPlaceProductsMapper.map(it) }
  }

  override fun getPlace(placeName: String): Maybe<PlaceDto> {
    return placeDao.getPlace(placeName.lowercase()).map { roomPlaceWithPlaceProductsMapper.map(it) }
  }
}