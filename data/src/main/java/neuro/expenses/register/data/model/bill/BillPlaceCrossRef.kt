package neuro.expenses.register.data.model.bill

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import neuro.expenses.register.data.model.place.RoomPlace

@Entity(
  primaryKeys = ["billId", "placeId"],
  indices = [Index(value = ["placeId"])],
  foreignKeys = [ForeignKey(
    entity = RoomBill::class,
    parentColumns = arrayOf("billId"),
    childColumns = arrayOf("billId"),
    onDelete = ForeignKey.NO_ACTION
  ), ForeignKey(
    entity = RoomPlace::class,
    parentColumns = arrayOf("placeId"),
    childColumns = arrayOf("placeId"),
    onDelete = ForeignKey.NO_ACTION
  )]
)
data class BillPlaceCrossRef(
  val billId: Long,
  val placeId: Long
)
