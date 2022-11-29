package neuro.expenses.register.entity.bill

import neuro.expenses.register.entity.model.BillItem

interface SelectBillIconUrl {
  /**
   * Select Bill iconUrl based on a list of BillItems.
   *
   * @param billItems list of BillItems.
   * @return iconUrl.
   */
  fun selectIconUrl(billItems: List<BillItem>): String
}