package neuro.expenses.register.entity.bill

import io.reactivex.rxjava3.core.Single

interface GenerateBillItemId {
  fun newId(): Single<Long>
}