package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.entity.model.Place

interface GetOrCreatePlace {
  /**
   * Get or create Place if it doesn't exist based on its name.
   *
   * @param name place name.
   * @return Single with the Place.
   */
  fun getOrCreatePlace(name: String): Single<Place>
}