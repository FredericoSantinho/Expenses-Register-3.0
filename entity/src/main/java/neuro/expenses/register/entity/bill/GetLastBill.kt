package neuro.expenses.register.entity.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.model.Bill

interface GetLastBill {
  fun getLastBill(): Maybe<Bill>
}