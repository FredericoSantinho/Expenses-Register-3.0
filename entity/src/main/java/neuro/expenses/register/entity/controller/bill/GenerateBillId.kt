package neuro.expenses.register.entity.controller.bill

import io.reactivex.rxjava3.core.Single

interface GenerateBillId {
  fun newId(): Single<Long>
}