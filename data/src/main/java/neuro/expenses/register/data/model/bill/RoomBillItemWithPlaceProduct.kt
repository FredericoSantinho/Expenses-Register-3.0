package neuro.expenses.register.data.model.bill

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import neuro.expenses.register.data.model.RoomPlaceProduct
import neuro.expenses.register.data.model.product.RoomPlaceProductWithProductAndCategory

data class RoomBillItemWithPlaceProduct(
  @Embedded val roomBillItem: RoomBillItem,
  @Relation(
    entity = RoomPlaceProduct::class,
    parentColumn = "billItemId",
    entityColumn = "placeProductId",
    associateBy = Junction(BillItemPlaceProductCrossRef::class)
  )
  val roomPlaceProduct: RoomPlaceProductWithProductAndCategory
)