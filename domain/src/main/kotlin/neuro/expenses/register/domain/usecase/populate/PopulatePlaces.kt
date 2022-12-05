package neuro.expenses.register.domain.usecase.populate

import io.reactivex.rxjava3.core.Completable

interface PopulatePlaces {
  /**
   * Populate places with dummy data.
   *
   * @return Completable that completes if operation succeeds.
   */
  fun populatePlaces(): Completable
}