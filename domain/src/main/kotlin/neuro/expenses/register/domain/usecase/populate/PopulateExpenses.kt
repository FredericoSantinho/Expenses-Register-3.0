package neuro.expenses.register.domain.usecase.populate

import io.reactivex.rxjava3.core.Completable

interface PopulateExpenses {
  /**
   * Populate expenses with dummy data.
   *
   * @return Completable that completes if operation succeeds.
   */
  fun populateExpenses(): Completable
}