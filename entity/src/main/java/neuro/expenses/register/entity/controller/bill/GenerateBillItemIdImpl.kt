package neuro.expenses.register.entity.controller.bill

import io.reactivex.rxjava3.core.Single

class GenerateBillItemIdImpl : GenerateBillItemId {
  override fun newId(): Single<Long> {
    return Single.just(0L)
  }
}