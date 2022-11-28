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
    onDelete = ForeignKey.CASCADE
  )], indices = [Index(value = ["placeProductId"])]
)
data class RoomBillItem(
  @PrimaryKey
  val billItemId: Long = 0,
  val amount: Double,
  val placeProductId: Long,
  val parentBillId: Long
)