package neuro.expenses.register.data.model.bill

import androidx.room.Entity
import androidx.room.Index

@Entity(
  primaryKeys = ["billId", "placeId"],
  indices = [Index(value = ["placeId"])]
)
data class BillPlaceCrossRef(
  val billId: Long,
  val placeId: Long
)
