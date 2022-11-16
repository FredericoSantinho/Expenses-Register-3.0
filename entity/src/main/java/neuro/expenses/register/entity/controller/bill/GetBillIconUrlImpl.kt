package neuro.expenses.register.entity.controller.bill

import neuro.expenses.register.entity.BillItem

class GetBillIconUrlImpl : GetBillIconUrl {
  override fun getIconUrl(billItems: List<BillItem>): String {
    return billItems.get(0).placeProduct.product.iconUrl
  }
}