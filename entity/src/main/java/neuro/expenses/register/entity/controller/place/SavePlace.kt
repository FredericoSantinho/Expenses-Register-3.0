package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.Place

interface SavePlace {
  fun savePlace(place: Place): Completable
}