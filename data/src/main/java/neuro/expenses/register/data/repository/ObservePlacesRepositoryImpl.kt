package neuro.expenses.register.data.repository

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.data.mapper.place.toDomain
import neuro.expenses.register.domain.dto.PlaceDto
import neuro.expenses.register.domain.repository.ObservePlacesRepository

class ObservePlacesRepositoryImpl(private val placeDao: PlaceDao) : ObservePlacesRepository {
  override fun observePlaces(): Observable<List<PlaceDto>> {
    return placeDao.observeAll().map { it.toDomain() }
  }
}