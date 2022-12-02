package neuro.expenses.register.domain.usecase.populate

import io.reactivex.rxjava3.core.Completable

interface PrePopulate {
  fun prePopulate(): Completable
}