package neuro.expenses.register.data.model.bill

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import neuro.expenses.register.data.model.RoomPlaceProduct

@Entity(
  primaryKeys = ["billItemId", "placeProductId"],
  indices = [Index(value = ["placeProductId"])],
  foreignKeys = [ForeignKey(
    entity = RoomBillItem::class,
    parentColumns = arrayOf("billItemId"),
    childColumns = arrayOf("billItemId"),
    onDelete = ForeignKey.NO_ACTION
  ), ForeignKey(
    entity = RoomPlaceProduct::class,
    parentColumns = arrayOf("placeProductId"),
    childColumns = arrayOf("placeProductId"),
    onDelete = ForeignKey.NO_ACTION
  )]

)
data class BillItemPlaceProductCrossRef(
  val billItemId: Long,
  val placeProductId: Long
)
