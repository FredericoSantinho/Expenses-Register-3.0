package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.model.Place

interface SavePlace {
  /**
   * Save a Place.
   *
   * @param place Place to save.
   * @return Completable that completes if operation succeeds.
   */
  fun savePlace(place: Place): Completable
}