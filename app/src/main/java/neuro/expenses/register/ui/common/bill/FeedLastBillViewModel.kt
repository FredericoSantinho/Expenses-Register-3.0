package neuro.expenses.register.ui.common.bill

import io.reactivex.rxjava3.disposables.Disposable

interface FeedLastBillViewModel {
  fun subscribe(): Disposable
}