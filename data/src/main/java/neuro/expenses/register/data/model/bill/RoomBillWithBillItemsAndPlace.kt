package neuro.expenses.register.data.model.bill

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import neuro.expenses.register.data.model.place.RoomPlace
import neuro.expenses.register.data.model.place.RoomPlaceWithPlaceProducts

data class RoomBillWithBillItemsAndPlace(
  @Embedded val roomBill: RoomBill,
  @Relation(
    entity = RoomBillItem::class,
    parentColumn = "billId",
    entityColumn = "parentBillId"
  )
  val billItems: List<RoomBillItemWithPlaceProduct>,
  @Relation(
    entity = RoomPlace::class,
    parentColumn = "billId",
    entityColumn = "placeId",
    associateBy = Junction(BillPlaceCrossRef::class)
  )
  val place: RoomPlaceWithPlaceProducts
)