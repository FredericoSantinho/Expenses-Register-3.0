package neuro.expenses.register.viewmodel.bill

import io.reactivex.rxjava3.core.Completable

interface FeedLastBillViewModel {
  fun observe(): Completable
}