package neuro.expenses.register.data.model.bill

import androidx.room.Entity
import androidx.room.Index

@Entity(
  primaryKeys = ["billItemId", "placeProductId"],
  indices = [Index(value = ["placeProductId"])]
)
data class BillItemPlaceProductCrossRef(
  val billItemId: Long,
  val placeProductId: Long
)
