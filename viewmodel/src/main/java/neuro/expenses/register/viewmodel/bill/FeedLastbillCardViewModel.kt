package neuro.expenses.register.viewmodel.bill

import io.reactivex.rxjava3.core.Completable

interface FeedLastbillCardViewModel {
  fun observe(): Completable
}