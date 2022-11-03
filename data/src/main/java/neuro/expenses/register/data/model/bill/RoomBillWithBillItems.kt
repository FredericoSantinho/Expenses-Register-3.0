package neuro.expenses.register.data.model.bill

import androidx.room.Embedded
import androidx.room.Relation

data class RoomBillWithBillItems(
  @Embedded val roomBill: RoomBill,
  @Relation(
    entity = RoomBillItem::class,
    parentColumn = "billId",
    entityColumn = "parentBillId"
  )
  val billItems: List<RoomBillItemWithPricedProduct>
)