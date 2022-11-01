package neuro.expenses.register.data.model.bill

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import neuro.expenses.register.data.model.RoomPricedProduct

data class RoomBillItemWithPricedProduct(
  @Embedded val roomBillItem: RoomBillItem,
  @Relation(
    entity = RoomPricedProduct::class,
    parentColumn = "billItemId",
    entityColumn = "pricedProductId",
    associateBy = Junction(BillItemPricedProductCrossRef::class)
  )
  val roomPricedProduct: List<RoomPricedProductWithProductAndCategory>
)