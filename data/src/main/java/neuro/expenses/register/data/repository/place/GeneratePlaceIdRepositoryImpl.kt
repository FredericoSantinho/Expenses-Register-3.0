package neuro.expenses.register.data.repository.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.data.dao.PlaceDao
import neuro.expenses.register.domain.repository.place.GeneratePlaceIdRepository

class GeneratePlaceIdRepositoryImpl(private val placeDao: PlaceDao) : GeneratePlaceIdRepository {
  override fun newId(): Single<Long> {
    return placeDao.getLastId().defaultIfEmpty(0).map { it + 1 }
  }
}