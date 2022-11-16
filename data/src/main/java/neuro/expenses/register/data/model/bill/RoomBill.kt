package neuro.expenses.register.data.model.bill

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bill_table")
data class RoomBill(
  @PrimaryKey(autoGenerate = true)
  var billId: Long = 0,
  val placeId: Long,
  val calendar: Calendar,
  val total: Double,
  val isOpen: Boolean,
  val iconUrl: String
)
