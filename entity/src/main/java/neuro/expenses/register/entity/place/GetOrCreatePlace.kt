package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.Place

interface GetOrCreatePlace {
  fun getOrCreatePlace(name: String): Single<Place>
}