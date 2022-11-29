package neuro.expenses.register.data.model.bill

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import neuro.expenses.register.data.model.place.RoomPlace
import java.util.*

@Entity(
  tableName = "bill_table", foreignKeys = [ForeignKey(
    entity = RoomPlace::class,
    parentColumns = arrayOf("placeId"),
    childColumns = arrayOf("placeId"),
    onDelete = ForeignKey.NO_ACTION
  )], indices = [Index(value = ["placeId"])]
)
data class RoomBill(
  @PrimaryKey
  var billId: Long,
  val placeId: Long,
  val calendar: Calendar,
  val total: Double,
  val isOpen: Boolean,
  val iconUrl: String
)
