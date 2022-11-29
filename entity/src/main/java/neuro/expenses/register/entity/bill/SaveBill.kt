package neuro.expenses.register.entity.bill

import neuro.expenses.register.entity.model.Bill

interface SaveBill {
  fun save(bill: Bill)
}