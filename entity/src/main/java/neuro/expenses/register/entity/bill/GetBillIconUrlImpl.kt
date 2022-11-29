package neuro.expenses.register.entity.bill

import neuro.expenses.register.entity.model.BillItem

class GetBillIconUrlImpl : GetBillIconUrl {
  override fun getIconUrl(billItems: List<BillItem>): String {
    return billItems.get(0).placeProduct.product.iconUrl
  }
}