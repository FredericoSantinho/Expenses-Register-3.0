package neuro.expenses.register.data.model.bill

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill_table")
data class RoomBill(
  val place: String,
  val timestamp: Long,
  val total: Double,
  val isOpen: Boolean,
  val iconUrl: String,
  @PrimaryKey(autoGenerate = true)
  var billId: Long = 0
)
