package neuro.expenses.register.entity.bill

import neuro.expenses.register.entity.model.Bill

interface SaveBill {
  /**
   * Save a Bill.
   *
   * @param Bill to save.
   */
  fun save(bill: Bill)
}