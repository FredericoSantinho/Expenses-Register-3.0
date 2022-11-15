package neuro.expenses.register.entity.controller.bill

import neuro.expenses.register.entity.Bill

interface SaveBill {
  fun save(bill: Bill)
}