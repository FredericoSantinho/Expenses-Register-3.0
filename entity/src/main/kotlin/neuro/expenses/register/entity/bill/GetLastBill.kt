package neuro.expenses.register.entity.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.model.Bill

interface GetLastBill {
  /**
   * Get last stored Bill if it exists.
   *
   * @return Maybe with the last stored bill or empty if no Bills are stored.
   */
  fun getLastBill(): Maybe<Bill>
}