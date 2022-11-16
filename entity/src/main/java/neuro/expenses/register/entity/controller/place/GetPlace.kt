package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.Place

interface GetPlace {
  fun getPlace(nameLowercase: String): Maybe<Place>
}