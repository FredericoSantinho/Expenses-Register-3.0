package neuro.expenses.register.data.model.place

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["placeId", "placeProductId"], indices = [Index(value = ["placeProductId"])])
data class PlacePlaceProductCrossRef(
  val placeId: Long,
  val placeProductId: Long
)
