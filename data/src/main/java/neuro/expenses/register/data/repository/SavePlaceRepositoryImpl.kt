package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.mapper.place.RoomPlaceMapper
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.SavePlaceRepository

class SavePlaceRepositoryImpl(private val placeDao: PlaceDao, private val roomPlaceMapper: RoomPlaceMapper) : SavePlaceRepository {
  override fun savePlace(placeDto: PlaceDto): Single<Long> {
    val roomPlace = roomPlaceMapper.map(placeDto)

    return placeDao.insert(roomPlace)
  }
}