package neuro.expenses.register.data.repository.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.mapper.place.toDomain
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.place.GetPlaceRepository

class GetPlaceRepositoryImpl(private val placeDao: PlaceDao) : GetPlaceRepository {
  override fun getPlace(placeId: Long): Maybe<PlaceDto> {
    return placeDao.getPlace(placeId).map { it.toDomain() }
  }

  override fun getPlace(placeNameLowerCase: String): Maybe<PlaceDto> {
    return placeDao.getPlace(placeNameLowerCase).map { it.toDomain() }
  }
}