package neuro.expenses.register.viewmodel.test

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.usecase.populate.PopulatePlaces

class PopulatePlacesViewModel(private val populatePlaces: PopulatePlaces) {
  fun populatePlaces(): Completable {
    return populatePlaces.populatePlaces()
  }
}