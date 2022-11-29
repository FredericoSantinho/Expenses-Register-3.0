package neuro.expenses.register.entity.bill

import neuro.expenses.register.entity.model.BillItem

interface GetBillIconUrl {
  fun getIconUrl(billItems: List<BillItem>): String
}