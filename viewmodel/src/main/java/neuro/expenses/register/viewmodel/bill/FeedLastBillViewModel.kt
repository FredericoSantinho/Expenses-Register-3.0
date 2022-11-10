package neuro.expenses.register.viewmodel.bill

import io.reactivex.rxjava3.disposables.Disposable

interface FeedLastBillViewModel {
  fun subscribe(): Disposable
}