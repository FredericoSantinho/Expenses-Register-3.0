package neuro.expenses.register.entity.bill

import neuro.expenses.register.entity.model.BillItem

class SelectBillIconUrlImpl : SelectBillIconUrl {
  override fun selectIconUrl(billItems: List<BillItem>): String {
    return billItems.get(0).placeProduct.product.iconUrl
  }
}