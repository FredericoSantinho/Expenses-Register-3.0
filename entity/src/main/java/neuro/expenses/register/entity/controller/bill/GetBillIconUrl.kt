package neuro.expenses.register.entity.controller.bill

import neuro.expenses.register.entity.BillItem

interface GetBillIconUrl {
  fun getIconUrl(billItems: List<BillItem>): String
}