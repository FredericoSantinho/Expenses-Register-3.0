package neuro.expenses.register.data.repository.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.mapper.place.toDomain
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.place.GetPlacesRepository

class GetPlacesRepositoryImpl(private val placeDao: PlaceDao) : GetPlacesRepository {
  override fun getPlaces(): Single<List<PlaceDto>> {
    return placeDao.getAll().map { it.toDomain() }
  }
}