package neuro.expenses.register.entity.controller.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.Bill

interface GetLastBill {
  fun getLastBill(): Maybe<Bill>
}