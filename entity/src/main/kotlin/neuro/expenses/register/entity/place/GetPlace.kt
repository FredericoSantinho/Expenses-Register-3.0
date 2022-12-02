package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.model.Place

interface GetPlace {
  /**
   * Get Place if it exists based on its name.
   *
   * @param name name in lowercase.
   * @return Maybe with the Place or empty if no Place is found.
   */
  fun getPlace(nameLowercase: String): Maybe<Place>
}