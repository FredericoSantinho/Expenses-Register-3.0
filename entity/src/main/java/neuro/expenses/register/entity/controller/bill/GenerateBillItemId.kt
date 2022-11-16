package neuro.expenses.register.entity.controller.bill

import io.reactivex.rxjava3.core.Single

interface GenerateBillItemId {
  fun newId(): Single<Long>
}