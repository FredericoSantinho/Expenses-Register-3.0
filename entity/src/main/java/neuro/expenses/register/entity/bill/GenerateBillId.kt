package neuro.expenses.register.entity.bill

import io.reactivex.rxjava3.core.Single

interface GenerateBillId {
  fun newId(): Single<Long>
}