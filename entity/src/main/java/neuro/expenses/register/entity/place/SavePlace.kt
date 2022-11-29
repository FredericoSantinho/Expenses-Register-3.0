package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.model.Place

interface SavePlace {
  fun savePlace(place: Place): Completable
}