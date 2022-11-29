package neuro.expenses.register.data.model.bill

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import neuro.expenses.register.data.model.RoomPlaceProduct

@Entity(
  tableName = "bill_item_table", foreignKeys = [ForeignKey(
    entity = RoomPlaceProduct::class,
    parentColumns = arrayOf("placeProductId"),
    childColumns = arrayOf("placeProductId"),
    onDelete = ForeignKey.NO_ACTION
  ), ForeignKey(
    entity = RoomBill::class,
    parentColumns = arrayOf("billId"),
    childColumns = arrayOf("billId"),
    onDelete = ForeignKey.NO_ACTION
  )], indices = [Index(value = ["placeProductId"]), Index(value = ["billId"])]
)
data class RoomBillItem(
  @PrimaryKey
  val billItemId: Long,
  val amount: Double,
  val placeProductId: Long,
  val billId: Long
)