package neuro.expenses.register.entity.bill

import neuro.expenses.register.entity.model.Bill

interface SaveBill {
  /**
   * Save a Bill.
   *
   * @param bill Bill to save.
   */
  fun saveBill(bill: Bill)
}