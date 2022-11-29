package neuro.expenses.register.entity.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.model.Place

interface GetPlace {
  fun getPlace(nameLowercase: String): Maybe<Place>
}