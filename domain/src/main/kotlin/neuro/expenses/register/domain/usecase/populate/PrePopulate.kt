package neuro.expenses.register.domain.usecase.populate

import io.reactivex.rxjava3.core.Completable

interface PrePopulate {
  /**
   * Pre Populate expenses and places.
   *
   * @return Completable that completes if operation succeeds.
   */
  fun prePopulate(): Completable
}