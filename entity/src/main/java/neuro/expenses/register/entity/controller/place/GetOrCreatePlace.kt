package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.Place

interface GetOrCreatePlace {
  fun getOrCreatePlace(name: String): Single<Place>
}